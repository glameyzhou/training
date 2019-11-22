package org.glamey.training.io.bio;

import com.google.common.base.Charsets;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou  2017/1/1.22.
 */
public class ClientDemo {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args)
            throws IOException, InterruptedException {
        int index = 1;
        String data;
        while (true) {
            Socket socket = new Socket("127.0.0.1", 2345);
            data = String.format("大家好,现在是[%d]->%s", index, DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data.getBytes(Charsets.UTF_8.name()));
            outputStream.flush();
            outputStream.close();
            socket.close();
            System.out.println(String.format("sending--->%s", data));
            index++;
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
