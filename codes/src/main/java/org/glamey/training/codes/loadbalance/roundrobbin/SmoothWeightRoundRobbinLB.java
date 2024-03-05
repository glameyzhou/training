package org.glamey.training.codes.loadbalance.roundrobbin;

import org.glamey.training.codes.loadbalance.LoadBalance;
import org.glamey.training.codes.loadbalance.domian.ServerIp;
import org.glamey.training.codes.loadbalance.domian.Weight;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 平滑权重轮询算法
 * 是针对权重轮询的比较更平滑的一种算法
 * https://tenfy.cn/2018/11/12/smooth-weighted-round-robin/
 * https://www.fanhaobai.com/2018/12/load-balance-smooth-weighted-round-robin.html
 * https://colobu.com/2016/12/04/smooth-weighted-round-robin-algorithm/
 * <p>
 * 1、每个节点，用当前值+权重  currentWeight += weight
 * 2、找到当前权重值最大的Weight，即为返回的节点 weight.ip
 * 3、将待返回的节点当前权重值减去总权重。weight.currentWeight -= sumWeight
 * <p>
 * 总权重 S = X1 + X2 + X3 + .... + Xn
 * <p>
 * N个节点当前权重的初始化 {0,0,....0}，长度为n,值均为0
 * 第一轮初始化 {X1,X2,X3....Xn}
 * 最大节点是j,那么j节点减去S  {X1,X2,X3..Xj-S..Xn}
 * <p>
 * <p>
 * X1 + X2 + X3 + ...+Xj-S + .. + Xn ==>
 * X1 + X2 + X3 + ... + Xn -S ==>
 * S - S ==>
 * 0
 */
public class SmoothWeightRoundRobbinLB implements LoadBalance {

    private static final Map<String, Weight> IP_WEIGHT_MAP = new LinkedHashMap<>();

    @Override
    public String getServer() {
        if (IP_WEIGHT_MAP.isEmpty()) {
            for (Map.Entry<String, Integer> entry : ServerIp.IP_WEIGHT_MAP.entrySet()) {
                IP_WEIGHT_MAP.put(entry.getKey(), new Weight(entry.getKey(), entry.getValue(), 0));
            }
        }
        int sumWeight = IP_WEIGHT_MAP.values().stream().mapToInt(Weight::getWeight).sum();
        Weight maxWeight = null;
        Collection<Weight> weights = IP_WEIGHT_MAP.values();
        for (Weight weight : weights) {
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
            if (maxWeight == null || weight.getCurrentWeight() > maxWeight.getCurrentWeight()) {
                maxWeight = weight;
            }
        }
        maxWeight.setCurrentWeight(maxWeight.getCurrentWeight() - sumWeight);
        return maxWeight.getIp();
    }


    public static void main(String[] args) {
        SmoothWeightRoundRobbinLB sm = new SmoothWeightRoundRobbinLB();
        for (int i = 0; i < 10; i++) {
            System.out.println(sm.getServer());
        }
    }
}
