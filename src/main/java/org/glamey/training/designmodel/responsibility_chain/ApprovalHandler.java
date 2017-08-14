package org.glamey.training.designmodel.responsibility_chain;

/**
 * @author zhouyang.zhou. 2017.08.14.16.
 */
public abstract class ApprovalHandler {
  protected ApprovalHandler nextApprovalHandler;

  public ApprovalHandler getNextApprovalHandler() {
    return nextApprovalHandler;
  }

  public void setNextApprovalHandler(ApprovalHandler nextApprovalHandler) {
    this.nextApprovalHandler = nextApprovalHandler;
  }

  protected abstract void handle(Applicant applicant);
}
