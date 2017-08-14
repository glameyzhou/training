package org.glamey.training.designmodel.responsibility_chain;

import java.math.BigDecimal;

/**
 * @author zhouyang.zhou. 2017.08.14.16.
 */
public class ManagerApprovalHandler extends ApprovalHandler {
  @Override public void handle(Applicant applicant) {
    BigDecimal money = applicant.getMoney();
    if (money.compareTo(new BigDecimal(500)) > 0 && money.compareTo(new BigDecimal(1000)) <= 0) {
      System.out.println("----> Manager 处理->完毕 ...");
    } else {
      ApprovalHandler nextApprovalHandler = getNextApprovalHandler();
      if (nextApprovalHandler != null) {
        System.out.println("----> 下一个人责任人处理 ...");
        nextApprovalHandler.handle(applicant);
      } else {
        System.out.println("----> 没有责任人了 ...");
      }
    }
  }
}
