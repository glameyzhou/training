package org.glamey.training.servlet.two;

import com.google.common.base.Stopwatch;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.03.14.
 */

@WebServlet(urlPatterns = "/blockServlet", description = "the block servlet")
public class BlockingServlet extends HttpServlet {

  @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Stopwatch stopwatch = Stopwatch.createStarted();
      TimeUnit.SECONDS.sleep(2);
      req.setAttribute("a1", "value1");
      req.setAttribute("a2", "value2");
      req.setAttribute("a3", "value3");

      Object value1 = req.getAttribute("a1");
      System.out.println("value1=" + value1);

      long duration = stopwatch.elapsed(TimeUnit.MILLISECONDS);
      resp.getWriter().printf("Thread %s completed the task in %s ms",
                              Thread.currentThread().getName(), duration);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
