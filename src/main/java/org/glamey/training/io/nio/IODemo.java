package org.glamey.training.io.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhouyang.zhou. 2017.01.10.16.
 */
public class IODemo {
  public static void main(String[] args) throws IOException {
    //writeToChannel();
    String filePath = "/tmp/demo.txt";
    //readFileByBIO(filePath);filePath
    readFileByNIO(filePath);
  }

  private static void readFileByBIO(String filePath) throws IOException {
    InputStream inputStream = new FileInputStream(new File(filePath));
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    String line;
    //reader.readLine()为阻塞读取
    while ((line = reader.readLine()) != null) {
      System.out.printf("-->[%s]\n", line);
    }
    reader.close();
    inputStream.close();
  }

  private static final void readFileByNIO(String filePath) throws IOException {
    RandomAccessFile file = new RandomAccessFile(filePath, "rw");
    FileChannel channel = file.getChannel();
    ByteBuffer buffer = ByteBuffer.allocate(10);
    int read = channel.read(buffer);
    while (read != -1) {
      System.out.printf("--->[%s]", read);
      buffer.flip();
    }
    while (buffer.hasRemaining()) {
      System.out.println((char) buffer.get());
    }
    buffer.clear();
    channel.read(buffer);
    file.close();
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
