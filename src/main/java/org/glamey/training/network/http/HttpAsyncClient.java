package org.glamey.training.network.http;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * @author zhouyang.zhou. 2017.10.08.05.
 */
public class HttpAsyncClient {
  private final CloseableHttpAsyncClient client;

  public HttpAsyncClient() {
    this.client = HttpAsyncClients.createDefault();
    this.client.start();
  }

  public void execute(HttpUriRequest request, FutureCallback<HttpResponse> responseFutureCallback) {
    client.execute(request, responseFutureCallback);
  }

  public void close() throws IOException {
    client.close();
  }
}
