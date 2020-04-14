package org.glamey.training.http;

import org.apache.http.client.methods.HttpUriRequest;

import java.util.List;

/**
 * @author zhouyang.zhou. 2017.10.08.05.
 */
public class ReplayApplication {
    public static void main(String[] args) throws InterruptedException {
        ReplyWithProblem replay1 = new ReplyWithProblem();

        //加载一万条请求数据放入缓存
        List<HttpUriRequest> requests = replay1.loadMockRequest(10000);

        //开始循环回放
        replay1.start(requests);
    }
}
