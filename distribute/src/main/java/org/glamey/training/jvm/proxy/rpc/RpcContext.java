package org.glamey.training.jvm.proxy.rpc;

import lombok.Data;

import java.util.Map;

@Data
public class RpcContext implements java.io.Serializable {
    private String methodName;
    private Map<String, Object> parameters;
}
