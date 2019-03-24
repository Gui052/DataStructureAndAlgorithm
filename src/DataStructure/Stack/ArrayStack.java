package DataStructure.Stack;

import DataStructure.Array.Array;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:47
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    public ArrayStack(int capaticy) {
        array = new Array<E>(capaticy);
    }

    public ArrayStack() {
        array = new Array<E>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E o) {
        array.addLast(o);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    @Override
    public E peek() {
        return array.getLast();
    }

    public int getCapaticy() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        res.append("]  top");
        return res.toString();
    }
}