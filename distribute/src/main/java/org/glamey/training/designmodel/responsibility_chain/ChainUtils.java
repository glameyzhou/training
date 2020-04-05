package org.glamey.training.designmodel.responsibility_chain;

/**
 * @author yang.zhou 2019.11.15.13
 */
public class ChainUtils {

    public static final void process(ApprovalHandler approvalHandler, Applicant applicant) {
        if (approvalHandler != null) {
            System.out.println(" --> 下一个节点处理");
            approvalHandler.handle(applicant);
        } else {
            System.out.println(" --> 链条结束");
        }
    }
}
