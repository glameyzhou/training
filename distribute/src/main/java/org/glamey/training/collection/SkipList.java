package org.glamey.training.collection;

/**
 * 跳表实现
 * 空间换时间
 * <p>
 * 无锁CAS算法实现
 * {@link java.util.concurrent.ConcurrentSkipListMap}   元素有序
 * {@link java.util.concurrent.ConcurrentSkipListSet}
 * <p>
 * 跳表（SkipList），是一种可以快速查找的数据结构，类似于平衡树。
 * 它们都可以对元素进行快速的查找。因为跳表是基于链表的，因此，它的插入和删除效率比较高。
 * 因此在高并发的环境下，如果是平衡树，你需要一个全局锁来保证整个树的线程安全，而对于跳表，你只需要局部锁来控制即可。
 * 对于查询而言，它的时间复杂度为O(log)。
 *
 * @author yang.zhou 2019.11.18.16
 */
public class SkipList<T extends Comparable<? super T>> {


}
