package Interview.LeetCode.dataStructure;

import java.util.Stack;

/** 链表相关问题
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  22:01
 */
public class LinkLists {
    class ListNode{
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * NO.160 找到两个链表的交点
     * 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
     *
     * 当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；同样地，当访问 B 链表的指针访问到链表尾部时，
     * 令它从链表 A 的头部开始访问链表 A。这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode l1 = headA;
        ListNode l2 = headB;
        while (l1 != l2) {
            l1 = (l1.next == null) ? headB : l1.next;
            l2 = (l2.next == null) ? headA : l2.next;
        }
        return l1;
    }

    /**
     * NO.206 链表反转--原地翻转
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        ListNode prev = null; //关键是这里，循环开始时
        while (temp.next != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        temp.next = prev;
        return temp; //最后一个结点需要手动赋值
    }
    //递归写法
    public ListNode reverseList1(ListNode head){
        if (head == null || head.next==null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList1(next);
        next.next = head;  //在下一次往下递归中这个next的next被附为null
        head.next = null;  //影响上一次递归传进来的，递归上一步骤的next的next
        return newHead;
    }

    /**
     * NO.21-归并两个有序列表<br/>
     * 递归写法
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2==null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2); //继续往下找小的，l1将在递归回来的时候被返回
            return l1; //这里l1比l2小，那么递归上一次的时候的next应该是l1。
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    //直接写法
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode(-1);
        ListNode last = newList;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                last.next = l1;
                l1 = l1.next;
            } else {
                last.next = l2;
                l2 = l2.next;
            }
            last = last.next;
        }
        if (l1 == null) {
            last.next = l2;
        } else {
            last.next = l1;
        }
        return newList.next;
    }

    /**
     * NO.83-从有序列表中删除重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode revhead = head;
        ListNode temp;
        while (head.next != null) {
            temp = head.next;
            while (head.val == temp.val) {
                if (temp.next != null) {
                    temp = temp.next;
                } else {
                    head.next = null;
                    return revhead;
                }
            }
            head.next = temp;
            head = temp;
        }
        return revhead;
    }
    //递归写法
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates1(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    /**
     *  NO.19 删除倒数第N个结点并返回结点头指针
     *  这里是使用了被删除结点和最后结点的相对位置。也可以直接求出长度后定位到删除结点的位置
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode rev = head;
        ListNode dele = head;
        while (n-- > 0) {  //这里没有做超出长度的判断
            dele = dele.next;
        } //找到差n的结点
        if (dele == null) { //删除第一个结点
            return rev.next;
        }
        while (dele.next != null) {
            head = head.next;
            dele = dele.next;
        }
        head.next = head.next.next;
        return rev;
    }

    /**
     * NO.24-交换链表中的相邻结点<br/>
     * 需要注意的是，需要有一个区记录本次交换的两个结点之前的一个，防止断链
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //第一个结点交换情况
        ListNode rev = head.next, temp = head.next, last = null;
        head.next = temp.next;
        temp.next = head;
        last = head;
        head = head.next;
        //剩下的情况。因为必须有一个记录head的上一个结点
        while (head != null && head.next != null) {
            temp = head.next;
            head.next = temp.next;
            last.next = temp;
            last = head;
            temp.next = head;
            head = head.next;
        }
        return rev;
    }
    //开辟空间法，相当于用一个结点，代替head的上一个结点
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) { //两个条件分别针对双数和单数个数的情况
            ListNode l1 = pre.next;
            ListNode l2 = pre.next.next;
            l1.next = l2.next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }

    /**
     * NO.445-两链表求和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = buildStack(l1);
        Stack<Integer> stack2 = buildStack(l2);
        ListNode rev = new ListNode(-1);//使用头插法记录结果
        int carry = 0;//进位
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) { //carry!=0是因为怕最高位还有进位
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int result = x + y + carry;
            carry = result / 10;
            ListNode res = new ListNode(result % 10);
            res.next = rev.next;
            rev.next = res;
        }
        return rev.next;
    }
    //创建栈
    private Stack<Integer> buildStack(ListNode l) {
        Stack<Integer> stack = new Stack<>();
        while (l != null) {
            stack.push(l.val);
            l = l.next;
        }
        return stack;
    }

    /**
     * 回纹链表，空的和只有一个元素的也是<br/>
     * 1->2       false <br/>
     * 1->2->2->1 true
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode node = head;
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode pre = new ListNode(-1);
        int i = 0;
        ListNode temp = null;
        while (i < length / 2) {
            temp = head.next;
            head.next = pre.next;
            pre.next = head;
            head = temp;
            i++;
        }
        pre = pre.next;
        if (length % 2 == 1) { //判断是不是偶数
            head = head.next;
        }
        i = 0;
        while (i < length / 2) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
            i++;
        }
        return true;
    }

    /**
     * NO.725-分割链表：把链表分隔成 k 部分，每部分的长度都应该尽可能相同，排在前面的长度应该大于等于后面的
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode node = root;
        while (node != null) {
            length++;
            node = node.next;
        }
        int mod = length % k; //求不均的分割
        int size = length / k; //均等分割
        ListNode[] ret = new ListNode[k];
        node = root;
        for (int i = 0; i < k && node != null; i++) {
            ret[i] = node;
            int curSize = size + (mod-- > 0 ? 1 : 0); //真实的分割。应对不能整除的情况，保障大的在前面
            for (int j = 0; j < curSize - 1; j++) { //-1是因为前面已经进去了一个元素
                node = node.next;
            }
            ListNode next = node.next; //分割
            node.next = null; //在for循环判空了
            node = next;
        }
        return ret;
    }

    /**
     * NO.328-链表元素按照索引的奇偶性聚集
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head; //奇数索引
        ListNode evenHand = head.next;//偶数开始的结点
        ListNode even = evenHand;//偶数索引
        while (odd != null && even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHand;//将偶数排在后面
        return head;
    }
}
