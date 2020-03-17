package org.glamey.training.io.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * https://www.cnblogs.com/yaowen/p/9726357.html#4514270
 */
@Slf4j
public class BrokenPipeDemo {

    public static class Client {
        public static void main(String[] args) {
            try {
                Socket socket = new Socket();
//                socket.setSoLinger(true, 0);// 之后要调用了close,就会发送RST
                socket.connect(new InetSocketAddress("127.0.0.1", 1234));
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("hello " + new Date()).getBytes());

                Thread.sleep(10000);

                socket.close();

                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static class Server {
        public static void main(String[] args) {
            try {
                ServerSocket serverSocket = new ServerSocket(1234);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int len = inputStream.read(buffer);
                System.out.println("recv:" + new String(buffer, 0, len));

                Thread.sleep(10000);

                socket.getOutputStream().write("after client close, write back 1".getBytes());
                socket.getOutputStream().write("after client close, write back 2".getBytes());

                System.out.println("server close");
                System.in.read();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
