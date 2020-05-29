/**
 * 二分查找的各种问题
 * 注意点：如果是奇数个数字，二分后的mid是在正中间。如果是偶数个，mid在左半侧。
 * 解题思路：
 * 1、从排好序的数据或者区间中找到中间位置的元素，判断该元素是否满足搜索条件，如果满足，停止搜索，直接返回。
 * 2、如果中间元素不满足，则从两边区域进行搜索，由于数据是排好序的，可以利用排除法确定从那个区间进行搜索。
 * 3、通过判断，如果返现元素是在左区间就去左区间检索，如果在右区间就去右区间检索。
 * <p>
 * 分为：
 * 递归解法 {@link org.glamey.training.codes.binarysearch.BinarySearchRecursion}
 * 非递归解法。 {@link org.glamey.training.codes.binarysearch.BinarySearchLoop}
 */
package org.glamey.training.codes.binarysearch;