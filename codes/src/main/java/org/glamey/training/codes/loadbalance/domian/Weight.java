package org.glamey.training.codes.loadbalance.domian;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weight {
    private String ip;
    private int weight;
    private int currentWeight;
}
