package Interview.JianZhi_Offer;

import java.util.ArrayList;
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
        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    private TreeNode preIn(int[] p, int pi, int pj, int[] n, int ni, int nj, HashMap map) {
        if (pi > pj) {
            return null;
        }
        TreeNode head = new TreeNode(p[pi]);
        int index = (int)map.get(p[pi]);
        head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index - 1, map);
        head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
        return head;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * 实际上就是找到一个旋转数组的最小值
     * @param array
     * @return
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
     * （先后次序不同算不同的结果）
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
}
