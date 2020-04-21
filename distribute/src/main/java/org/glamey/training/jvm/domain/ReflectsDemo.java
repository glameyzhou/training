package org.glamey.training.jvm.domain;

import org.glamey.training.jvm.ProductionVO;
import org.glamey.training.jvm.Reflects;

import java.util.Date;

public class ReflectsDemo {

    public static void main(String[] args) {
        ProductionVO productionVO = new ProductionVO();
        productionVO.setId(new Long(123));
        productionVO.setCreateTime(new Date());
        productionVO.setUpdateTime(new Date());
        productionVO.setCode("p_123");
        productionVO.setName("p_name");


        String name = Reflects.getFieldValue(productionVO, "name");
        Date createTime = Reflects.getFieldValue(productionVO, "createTime");
        System.out.println(name + " " + createTime);

    }
}
