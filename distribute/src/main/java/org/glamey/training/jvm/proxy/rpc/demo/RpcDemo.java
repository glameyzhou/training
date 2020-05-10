package org.glamey.training.jvm.proxy.rpc.demo;

import org.glamey.training.jvm.proxy.rpc.ProxyManager;

import java.math.BigDecimal;

public class RpcDemo {

    public static void main(String[] args) {
        PayApi payApi = new ProxyManager<>(PayApi.class).getInstance();
        PayReq payReq = new PayReq();
        payReq.setCode("p_009");
        payReq.setMoney(new BigDecimal("100.02"));
        PayResp payResp = payApi.pay(payReq);
        System.out.println(payResp);
    }
}
