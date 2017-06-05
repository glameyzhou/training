package org.glamey.training.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhouyang.zhou. 2017.01.10.16.
 */
public class IODemo {

    public static void main(String[] args) throws IOException {
        writeToChannel();
    }

    private static void writeToChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("c:/temp.txt", "rw");
        FileChannel channel = file.getChannel();
        String data = "长江长江，我是黄河。";
        ByteBuffer buffer = ByteBuffer.allocate(48);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
    }
}
