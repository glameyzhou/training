package org.glamey.training.algorithm.loadbalance.domian;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weight {
    private String ip;
    private int weight;
    private int currentWeight;
}
