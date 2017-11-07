package org.glamey.training.designmodel.observable.observer;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Executor;
import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;

/**
 * @author zhouyang.zhou. 2017.11.07.17.
 */
public class BroadCaster {

  private List<EventListener> list;

  private Executor executor;

  public BroadCaster() {
    list = Lists.newArrayList();
  }

  public BroadCaster(Executor executor) {
    list = Lists.newArrayList();
    this.executor = executor;
  }

  public void register(EventListener listener) {
    list.add(listener);
  }

  public void remove(EventListener listener) {
    list.remove(listener);
  }

  public void publishEvent(final Event event) {
    IterableUtils.forEach(list, new Closure() {
      @Override public void execute(final Object input) {
        if(executor == null) {
          ((EventListener) input).onEventListener(event);
        } else {
          executor.execute(new Runnable() {
            @Override public void run() {
              ((EventListener) input).onEventListener(event);
            }
          });
        }
      }
    });
  }
}
