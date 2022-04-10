package interview.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/
 * 整型的LRU
 */
public class LRUCache {

    private static class ListNode {
        private int key;
        private int val;
        private ListNode next;
        private ListNode prev;
        public ListNode(int key,int val){
            this.key = key;
            this.val = val;
        }
        public ListNode() {}
    }
    //结点容量
    private final int capacity;
    private final Map<Integer, ListNode> map;
    private final ListNode head;
    private final ListNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    public void put(int key, int value) {
        //如果包含
        if (map.containsKey(key)) {
            ListNode temp = map.get(key);
            temp.val = value;
            moveNodeToEnd(temp);
            return;
        }
        //如果队列满了，删除最早的
        if (map.size() == capacity) {
            map.remove(head.next.key);
            removeNode(head.next);
        }
        //其余情况直接添加到最后
        ListNode node = new ListNode(key,value);
        map.put(key,node);
        addNodeToEnd(node);
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            moveNodeToEnd(node);
            return node.val;
        } else {
            return -1;
        }
    }

    private void removeNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addNodeToEnd(ListNode node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        node.prev.next = node;
    }

    private void moveNodeToEnd(ListNode node) {
        removeNode(node);
        addNodeToEnd(node);
    }
}
