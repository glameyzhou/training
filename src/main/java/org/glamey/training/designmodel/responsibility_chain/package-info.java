/**
 * 责任链模式是把多个对象串联起来形成一个链状结构，让每个对象都有机会对事件发送者的请求进行处理。
 * 责任链模式是设计模式中的行为模式，设计意图是为了使事件发送者和事件接受者之间解耦。
 * 通常责任链链中的每个对象都有下一个对象的引入(例如tomcat 里面StandardPipeline用来管理valve),
 * 或者有个同一个链管理工厂里面使用数组存放了所有的对象（例如tomcat里面ApplicationFilterChain用来关系filter）。
 *
 * @author zhouyang.zhou. 2017.08.14.16.
 */
package org.glamey.training.designmodel.responsibility_chain;