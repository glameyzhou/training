package org.glamey.training.jvm;

import lombok.Data;
import org.glamey.training.jvm.domain.BaseDomain;

@Data
public class ProductionVO extends BaseDomain {
    private String code;
    private String name;
}
