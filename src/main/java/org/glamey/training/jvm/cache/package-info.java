/**
 * CPU具有多级缓存，越接近CPU缓存越小速度越快。
 * CPU缓存中的数据，以缓存行为单位处理。
 * CPU缓存行能带来免费加载数据的好处，所以处理数据速度非常快。
 * CPU缓存行也能带来弊端，多线程处理不相干的变量时会互相影响，也即使伪共享。
 * 避免伪共享的主要思路是：让不相干的变量不要存在同一个缓存行上。
 * 每两个long变量中间新增7个long类型。
 * 创建自己的long类型，而不是原生的。
 * 使用java8注解@sun.misc.Conteded，形如ConcurrentHashMap```中的```size()```统计map大小的时候，其中java.util.concurrent.ConcurrentHashMap.CounterCell类上面就是Contended```注解，来避免出现伪共享。
 *
 * @author yang.zhou 2020.01.19.15
 */
package org.glamey.training.jvm.cache;