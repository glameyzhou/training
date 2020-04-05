package org.glamey.training.designmodel.responsibility_chain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author zhouyang.zhou. 2017.08.14.16.
 */
@Getter
@Builder
public class Applicant {
    private String name;
    private String subject;
    private BigDecimal money;
}
