package datastructure.queue;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:45
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}