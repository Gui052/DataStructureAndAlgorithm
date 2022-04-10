package interview.jianzhi_offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ListAndQueue  {

    /**
     * 用栈模拟队列
     */
    public static class SimulateList{
        Stack<Integer> in = new Stack<>();
        Stack<Integer> out = new Stack<>();

        public void push(int node) {
            out2in();
            in.push(node);
        }

        public int pop() {
            in2out();
            return out.pop();
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
    }


    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序(可能的出栈顺序)。
     * 假设压入栈的所有数字均不相等。
     */
    public boolean isPopOrder(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        //每次入栈一个元素后，都要判断一下栈顶元素是不是当前出栈序列 popSequence 的第一个元素，
        // 如果是的话则执行出栈操作并将 popSequence 往后移一位，继续进行判断。
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty()
                    && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 在有序数组中找出两个数，使得和为给定的数 S。如果有多对数字的和等于 S，输出两个数的乘积最小的
     * 使用双指针，一个指针指向元素较小的值，一个指针指向元素较大的值。
     * 指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
     */
    public ArrayList<Integer> findNumbersForSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (j > i) {
            int cur = nums[i] + nums[j];
            if (cur == target) {
                return new ArrayList<>(Arrays.asList(nums[i], nums[j]));
            }
            if (cur < target) {
                i++;
            } else {
                j--;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1;
        int end = 2;
        int curSum = 3;
        while (end < sum) {
            //减掉小的
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                ret.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }
}