/**
 * description the load balance algorithm
 * <p>
 * hash
 * robbin
 * least connection
 * <p>
 * 1、随机、权重+随机
 * 2、轮询、权重+轮询
 * 3、平滑+权重+轮询  nginx 默认算法（平滑加权轮询算法） SmoothWeightRoundRobbin
 *
 * @author yang.zhou 2019.11.04.10
 */
package org.glamey.training.codes.loadbalance;