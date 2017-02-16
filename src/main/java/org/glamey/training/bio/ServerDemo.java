package org.glamey.training.bio;

import com.google.common.base.Charsets;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.IOUtils;

/**
 * @author zhouyang.zhou  2017/1/1.22.
 */
public class ServerDemo {

  private static final ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws IOException {
    final ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress(2345));
    while (true) {
      threadPool.execute(new Runnable() {
        public void run() {
          try {
            accept(serverSocket);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });
    }
  }

  private static void accept(ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    InputStream inputStream = socket.getInputStream();
    String string = IOUtils.toString(inputStream, Charsets.UTF_8.name());
    System.out.println(String.format("receiving--->%s", string));
    socket.close();
  }
}
