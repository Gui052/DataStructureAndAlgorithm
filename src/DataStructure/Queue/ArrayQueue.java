package DataStructure.Queue;

import DataStructure.Array.Array;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:45
 */
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayQueue() {
        array = new Array<E>();
    }

    /**
     * 获取当前数据长度
     * @return
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 获取容量
     * @return
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首元素
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        res.append("]  tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 1) {
                System.out.println(queue.dequeue());
                System.out.println(queue);
            }
        }
    }
}

