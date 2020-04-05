package org.glamey.training.spring.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhouyang.zhou. 2017.07.20.14.
 */
public class DemoMain {
    public static void main(String[] args) {
        String applicationPath = "spring/applicationContext.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(applicationPath);
        DemoPublisher demoPublisher = (DemoPublisher) context.getBean("demoPublisher");
        for (int i = 0; i < 10; i++) {
            String s = "this is the message->" + i;
            System.out.println(s);
            demoPublisher.publish(s);
        }
    }
}
