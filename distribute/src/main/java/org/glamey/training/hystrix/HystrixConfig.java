package org.glamey.training.hystrix;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCacheAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {


    @Bean
    public HystrixCacheAspect hystrixCacheAspect() {
        return new HystrixCacheAspect();
    }
}
