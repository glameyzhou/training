package org.glamey.training.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class HelloService {


    public static void main(String[] args) {
        int[] nums = new int[]{1, 10, 8, 20, 89, 3};
        quicksort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private static void quicksort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = getPartitionIndex(nums, left, right);
            quicksort(nums, left, partitionIndex - 1);
            quicksort(nums, partitionIndex + 1, right);
        }
    }


    private static int getPartitionIndex(int[] nums, int left, int right) {
        int p = left, pVal = nums[p];
        while (left < right) {

            while (left < right && nums[right] >= pVal) {
                right--;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= pVal) {
                left++;
            }
            nums[right] = nums[left];

            if (left == right) {
                nums[left] = pVal;
            }
        }
        return left;
    }


    @HystrixCommand(
            fallbackMethod = "fallbackSay",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")},
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public String say(String name) {
        try {
            return "say -> " + name;
        } catch (Exception e) {
            log.error("error", e);
        }
        return null;
    }

    public String fallbackSay(String name) {
        return "fallback_say -> " + name;
    }
}
