package org.glamey.grpc.server;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import org.glamey.grpc.proto.GreeterGrpc;
import org.glamey.grpc.proto.HelloRequest;
import org.glamey.grpc.proto.HelloResponse;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class HelloWorldServer {

    private final int port = 50051;
    private Server server;

    public void start() {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterGrpc.GreeterImplBase() {
                    @Override
                    public void sayHi(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
                        super.sayHi(request, responseObserver);
                    }
                })
                .build();
    }

    public static void main(String[] args) {
        int[] nums = IntStream.rangeClosed(0, 9).toArray();
        System.out.println("原始数据 " + Arrays.toString(nums));
        shuffle(nums);
        System.out.println("洗牌后 " + Arrays.toString(nums));
        sort(nums, 0, nums.length - 1);
        System.out.println("排序后 " + Arrays.toString(nums));
    }

    private static void sort(int[] nums, int left, int right) {
        if (nums == null || nums.length == 1 || left > right) {
            return;
        }
        int i = left, j = right, pVal = nums[left];
        while (i < j) {
            while (i < j && pVal <= nums[j]) {
                j--;
            }
            nums[i] = nums[j];

            while (i < j && pVal >= nums[i]) {
                i++;
            }
            nums[j] = nums[i];

            if (i == j) {
                nums[i] = pVal;
            }
        }
        sort(nums, left, i - 1);
        sort(nums, i + 1, right);
    }

    private static void shuffle(int[] nums) {
        if (nums == null || nums.length == 1) {
            return;
        }
        Random random = new Random();
        for (int i = nums.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        if (nums == null || nums.length == 1 || i == j) {
            return;
        }
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
