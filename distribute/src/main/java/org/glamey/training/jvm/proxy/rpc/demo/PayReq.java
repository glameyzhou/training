package org.glamey.training.jvm.proxy.rpc.demo;

import lombok.Data;
import org.glamey.training.jvm.proxy.rpc.RpcContext;

import java.math.BigDecimal;

@Data
public class PayReq extends RpcContext {
    private String code;
    private BigDecimal money;
}
