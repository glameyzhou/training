package org.glamey.training.servlet.three;

import com.google.common.base.Stopwatch;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.14.
 */

@WebServlet(urlPatterns = "/asyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Thread thread = Thread.currentThread();

    System.out.printf("async request servlet thread [start] --> %d:%s\n",
                      thread.getId(), thread.getName());

    Stopwatch stopwatch = Stopwatch.createStarted();
    AsyncContext context = req.startAsync();
    context.addListener(new AppAsyncListener());
    /**
     * 超时时间如果小于process时长，timeout
     *
     * {@link AppAsyncListener#onTimeout(AsyncEvent)}
     */
    context.setTimeout(10000L);

    ThreadPoolExecutor executor = (ThreadPoolExecutor) req.getServletContext().getAttribute(ServletConstants.EXECUTOR);
    executor.execute(new AsyncRequestProcessor(context, ServletConstants.PROCESS_TIME_MS));
    long elapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);
    System.out.printf("async request servlet thread [finish]--> %d:%s, %d ms\n",
                      thread.getId(), thread.getName(), elapsed);
  }
}
