/**
 * 一致性hash
 * 圆环存放2^32个节点，将真实节点做虚拟处理，默认的虚拟因子是160
 * <p>
 * <p>
 * TreeMap<Long,ShardInfo> key=hash,value=node info
 * Map<ShardInfo, NodeResource> key=node info, value=node resource
 * <p>
 *
 * @author yang.zhou 2019.11.04.18
 */
package org.glamey.training.codes.hash.consistent;