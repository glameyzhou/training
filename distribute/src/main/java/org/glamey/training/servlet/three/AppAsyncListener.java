package org.glamey.training.servlet.three;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.16.
 */
@WebListener
public class AppAsyncListener implements AsyncListener {
  @Override public void onComplete(AsyncEvent event) throws IOException {
    System.out.printf("async process was completed\n");
  }

  @Override public void onTimeout(AsyncEvent event) throws IOException {
    System.out.printf("async process was timeout\n");
    ServletResponse response = event.getAsyncContext().getResponse();
    PrintWriter out = response.getWriter();
    out.write("TimeOut Error in Processing");
    out.flush();
    out.close();
  }

  @Override public void onError(AsyncEvent event) throws IOException {
    System.out.printf("async process was error\n");
    ServletResponse response = event.getAsyncContext().getResponse();
    PrintWriter out = response.getWriter();
    out.write("error on processing");
    out.flush();
    out.close();
  }

  @Override public void onStartAsync(AsyncEvent event) throws IOException {
    System.out.printf("async process is started\n");
  }
}
