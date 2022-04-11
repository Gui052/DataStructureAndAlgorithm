package datastructure.queue;

/** 链队列
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:46
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10); //直接调用有参数的构造函数
    }

    /**
     * 装的元素个数
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 当前的元素个数
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        //扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        //第一种遍历方式
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        //需要更改front和tail
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        //缩容，但是值不应该等于0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Oueue is empty");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d,capacity=%d\n", size, getCapacity()));
        res.append("front [");
        //第二种遍历方式
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("]  tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<Integer>();
        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 1) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
