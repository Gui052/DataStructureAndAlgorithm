package DataStructure.Array;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:44
 */
public class Array<E> {
    private E[] data;
    private int size;

    /**
     *
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参数构造函数，默认数组长度10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取元素个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向所有元素之后添加元素
     * @param a 元素
     */
    public void addLast(E a) {
        add(size, a);
    }

    /**
     * 在前一个位置添加元素
     * @param a 元素
     */
    public void addFirst(E a) {
        add(0, a);
    }

    /**
     * 向数组中特定位置插入元素
     * @param index 位置
     * @param e 元素
     */
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("AddLast failed.Require index>0 and index < size");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,index is illegal");
        }
        return data[index];
    }

    /**
     * 设置元素
     * @param index
     * @param e
     */
    public void set(int index,E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,index is illegal");
        }
        data[index] = e;
    }

    /**
     * 搜索元素是否存在
     * @param e
     * @return
     */
    public boolean Contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取特定元素的位置
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除index位置的元素，并返回此元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed,index is illegal");
        }
        E ret = data[index];
        for (int i = index+1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        //动态减少,Lazy策略
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组删除特定元素
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 获取最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    //这里是自动扩容
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size=%d,capacity=%d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

}
