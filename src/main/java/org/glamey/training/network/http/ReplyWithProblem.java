package org.glamey.training.network.http;

import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;

import java.util.List;

/**
 * @author zhouyang.zhou. 2017.10.08.05.
 */
public class ReplyWithProblem {

    public List<HttpUriRequest> loadMockRequest(int num) {
        List<HttpUriRequest> requests = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            HttpGet httpGet = new HttpGet("http://www.baidu.com?a=" + i);
            requests.add(httpGet);
        }
        return requests;
    }

    public void start(List<HttpUriRequest> requests) {
        HttpAsyncClient httpClient = new HttpAsyncClient();
        int i = 0;
        while (true) {
            final HttpUriRequest request = requests.get(i);
            httpClient.execute(request, new FutureCallback<HttpResponse>() {
                public void completed(final HttpResponse response) {
                    System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                }

                public void failed(final Exception ex) {
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    System.out.println(request.getRequestLine() + " cancelled");
                }
            });
            i++;
            //TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
