package org.glamey.training.designmodel.observable.observer;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.concurrent.Executor;

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

    if (CollectionUtils.isEmpty(list)) {
      return;
    }

    list.stream().forEach(listener -> {
      if (executor == null) {
        listener.onEventListener(event);
      } else {
        executor.execute(() -> listener.onEventListener(event));
      }
    });
  }
}
