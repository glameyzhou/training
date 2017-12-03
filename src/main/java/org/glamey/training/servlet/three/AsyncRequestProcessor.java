package org.glamey.training.servlet.three;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import javax.servlet.AsyncContext;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.16.
 */
public class AsyncRequestProcessor implements Runnable {

  private AsyncContext context;
  private Long processTimeMs;

  public AsyncRequestProcessor(AsyncContext context, Long processTimeMs) {
    this.context = context;
    this.processTimeMs = processTimeMs;
  }

  @Override public void run() {

    System.out.printf("Async Supported ? %s\n",
                      context.getRequest().isAsyncSupported());

    process(processTimeMs);

    try {
      PrintWriter writer = context.getResponse().getWriter();
      writer.write("processing done for " + processTimeMs + " ms");
      writer.flush();
      writer.close();
      context.complete();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void process(Long processTimeMs) {
    try {
      Thread thread = Thread.currentThread();
      System.out.printf("开始业务处理 %d:%s\n", thread.getId(), thread.getName());
      TimeUnit.MILLISECONDS.sleep(processTimeMs);
      System.out.printf("结束业务处理 %d:%s\n", thread.getId(), thread.getName());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
