package org.glamey.training.jvm.proxy.rpc;

import lombok.Data;

@Data
public class RpcResult<T> implements java.io.Serializable {
    private int status;
    private String message;
    private T data;
}
