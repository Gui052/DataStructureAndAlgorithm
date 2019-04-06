package Interview.JianZhi_Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author lan
 * @version 1.0.0
 * @since 4/5/2019 5:25 PM
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 从一个上下左右的递增二维数组中找到一个元素。
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        int len = array.length - 1;
        int i = 0;
        while ((len >= 0) && (i < array[0].length)) {
            if (array[len][i] > target) {
                len--;
            } else if (array[len][i] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 将字符串中替换空格成为 %20
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                res.append("%20");
            } else {
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }

    /**
     * 把一个链表倒着插入arraylist。使用栈实现
     *
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            array.add(stack.pop()); //系统自带的这个ArrayList只有add方法
        }
        return array;
    }

    //使用头插法反转链表实现。
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ListNode temp = new ListNode(-1);
        temp.next = null;
        while (listNode != null) {
            ListNode next = listNode.next;
            listNode.next = temp.next;
            temp.next = listNode;
            listNode = next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        temp = temp.next;
        while (temp != null) {
            ret.add(temp.val);
            temp = temp.next;
        }
        return ret;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 重建二叉树。
     * @param pre 前序遍历结点序列
     * @param in 中序遍历结点序列
     * @return 二叉树
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        return preIn(pre, 0, pre.length - 1, 0, map);
    }

    private TreeNode preIn(int[] pre, int preL, int preR, int inL, HashMap map) {
        if (preL > preR)
            return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = (int) map.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = preIn(pre, preL + 1, preL + leftTreeSize, inL, map);
        root.right = preIn(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1, map);
        return root;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * 实际上就是找到一个旋转数组的最小值
     */
    public int minNumberInRotateArray(int [] array) {
        if (array.length==0) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return array[0];
    }

    /**
     * 斐波那契数列。下一项的是上两项的和
     * 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     */
    public int Fibonacci(int n) {
        int a = 1, b = 1, c = 0;
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
     * （先后次序不同算不同的结果）.结果集合也是一个斐波那契数列
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if(target==-1)
            return 0;
        else if(target==1)
            return 1;
        else if(target==2)
            return 2;
        else
            return JumpFloor(target-1)+JumpFloor(target-2);
    }
    //非递归写法
    public int JumpFloor2(int n) {
        if (n <= 2) {
            return n;
        }
        int pre1=1;
        int pre2 = 2;
        int result = 1;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶
     * 总共有多少种跳法。 动态规划问题
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target - 1];
    }
    //数学解法
    public int JumpFloorII2(int target) {
        return (int) Math.pow(2, target - 1);
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地
     * 覆盖一个2*n的大矩形，总共有多少种方法？
     * @param target
     * @return
     */
    public int RectCover(int target) {
        if (target <= 2) {
            return target;
        }
        int pre1 = 1, pre2 = 2, result = 0;
        for (int i = 3; i <= target; i++) {
            result = pre1 + pre2;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 与位运算去除 n 的位级表示中最低的那一位。比如12的二进制1100，和11（1011）与运算后删除了表示
     * 最低位那个，变成了1000。
     */
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * n%2=0：x^n = (x*x)^(n/2)
     * n%2=1：x^n = x*(x*x)^(n/2)
     */
    public double Power(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        boolean isNagative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNagative = true;
        }
        double pow = Power(base * base, exponent >> 1); //exponent/2
        if (exponent % 2 != 0) {
            pow = pow * base;
        }
        return isNagative ? 1 / pow : pow;
    }
}
