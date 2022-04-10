package interview.leetcode.dataStructure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/** 栈和队列
 * @author lan
 * @version 1.0.0
 * @since 2019/3/25  16:37
 */
public class StackAndQueue {
    /**
     * NO.232-用栈实现队列、使用两个栈实现。
     */
    class MyQueue {
        private Stack<Integer> in;
        private Stack<Integer> out;

        public MyQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void push(int x) {
            out2in();
            in.push(x);
        }

        public int pop() {
            in2out();
            return out.pop();
        }

        public int peek() {
            in2out();
            return out.peek();
        }

        private void in2out() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
        }

        private void out2in() {
            if (in.isEmpty()) {
                while (!out.isEmpty()) {
                    in.push(out.pop());
                }
            }
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /**
     * NO.225- 用队列实现栈<br/>
     * 将 x 插入队列尾部之后，需要让除了 x 之外的所有元素出队列，再入队列，整个队列保证顺序跟栈一样
     */
    class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>(); //队列的初始化竟然是这个我擦
        }

        public void push(int x) {  //在push添加逻辑，避免在pop和top重复添加逻辑
            queue.add(x);
            int cnt = queue.size();
            while (cnt-- > 1) {
                queue.add(queue.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * NO.155-实现最小值栈<br/>
     * 使用一个辅助栈记录最小值。只要求获得当前栈中的最小元素而已
     */
    class MinStack {
        private Stack<Integer> stack;
        private Stack<Integer> minstack;
        private int min;

        public MinStack() {
            stack = new Stack<>();
            minstack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            stack.push(x);
            min = Math.min(min, x);
            minstack.push(min);
        }

        public void pop() {
            stack.pop();
            minstack.pop();
            min = minstack.isEmpty() ? Integer.MAX_VALUE : minstack.peek();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minstack.peek();
        }
    }


    /**
     * 实现括号匹配
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char achar = s.charAt(i);
            if (achar == '(' || achar == '[' || achar == '{') {
                stack.push(achar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char stackTop = stack.pop();
                if (achar == ')' && stackTop != '(') {
                    return false;
                }
                if (achar == ']' && stackTop != '[') {
                    return false;
                }
                if (achar == '}' && stackTop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * NO.739- 求数组中这个数的后一个比它大的元素和它的距离。
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] dist = new int[n]; //这个数组的初始值都是0，所以对于没有比当前元素大的数不必处理
        Stack<Integer> indexs = new Stack<>();
        for (int curIndex = 0; curIndex < n; curIndex++) {
            //下面的循环处理的是如果一个数比前几个都大的情况。栈存的是索引
            while (!indexs.isEmpty() && T[curIndex] > T[indexs.peek()]) {
                int preIndex = indexs.pop();//出栈
                dist[preIndex] = curIndex - preIndex;
            }
            indexs.push(curIndex);
        }
        return dist;
    }

    /**
     *NO.503 循环数组中找到比一个数大的第一个数
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);//统一赋值
        Stack<Integer> pre = new Stack<>();
        for (int i = 0; i < n * 2; i++) { //乘以2的目的是为最后的元素找一遍数组就足够了
            int thisnum = nums[i % n]; //循环
            while (!pre.isEmpty() && nums[pre.peek()] < thisnum) {
                next[pre.pop()] = thisnum;
            }
            if (i < n) { //只在第一遍的时候加入索引
                pre.push(i);
            }
        }
        return next;
    }
}
