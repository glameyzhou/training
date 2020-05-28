package org.glamey.training.codes.leetcode;

import java.util.Stack;

public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); //  --> 返回 -3.
        minStack.pop();
        minStack.top();    //  --> 返回 0.
        minStack.getMin(); //  --> 返回 -2.
    }

    Stack<Integer> stack;
    int min;

    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int e) {
        if (e <= min) {
            stack.push(min);
            min = e;
        }
        stack.push(e);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
