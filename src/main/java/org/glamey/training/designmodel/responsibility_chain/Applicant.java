package org.glamey.training.designmodel.responsibility_chain;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

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
