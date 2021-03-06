package org.glamey.training.designmodel.responsibility_chain;

import java.math.BigDecimal;

/**
 * @author zhouyang.zhou. 2017.08.14.16.
 */
public class ChainBootStrap {

    public static void main(String[] args) {
        Applicant applicant = Applicant.builder()
                .name("张三")
                .subject("团建费用")
                .money(new BigDecimal(600))
                .build();

        ApprovalHandler tlApprovalHandler = new TLApprovalHandler();
        tlApprovalHandler.setNextApprovalHandler(new ManagerApprovalHandler());
        tlApprovalHandler.handle(applicant);
    }
}
