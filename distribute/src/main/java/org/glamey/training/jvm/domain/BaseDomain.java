package org.glamey.training.jvm.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDomain {
    private Long id;
    private Date createTime;
    private Date updateTime;
}
