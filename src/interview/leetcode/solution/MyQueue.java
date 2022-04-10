package interview.leetcode.solution;

import java.util.Stack;

/**
 * 232. 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class MyQueue {
    Stack<Integer> inPut = new Stack<>();
    Stack<Integer> outPut = new Stack<>();

    public void push(int x) {
        out2in();
        inPut.push(x);
    }

    public int pop() {
        in2out();
        return outPut.pop();
    }

    public int peek() {
        in2out();
        return outPut.peek();
    }

    public boolean empty() {
        return inPut.isEmpty() && outPut.isEmpty();
    }

    public void in2out() {
        while (!inPut.isEmpty()) {
            outPut.push(inPut.pop());
        }
    }

    public void out2in() {
        while (!outPut.isEmpty()) {
            inPut.push(outPut.pop());
        }
    }
}
