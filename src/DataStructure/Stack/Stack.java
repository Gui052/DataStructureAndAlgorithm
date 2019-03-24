package DataStructure.Stack;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:47
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
