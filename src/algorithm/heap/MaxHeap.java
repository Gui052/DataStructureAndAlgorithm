package algorithm.heap;

/**
 * 大根堆<br/>
 * 特点：完全二叉树。根节点大于左右孩子结点的值。一个结点i的父亲结点是i/2，左孩子是2*i，右孩子是2*i+1.<br/>
 * 如果用数组以1为索引存储，第一个不是叶子结点的结点索引为  最后一个叶子结点的索引/2
 *
 * @author lan
 * @since 2019/3/19  16:53
 */
public class MaxHeap {
    private final int[] data;
    /**
     * 数量
     */
    private int count;
    /**
     * 容量
     */
    private final int capacity;

    public MaxHeap(int capacity) {
        this.data = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(int[] arr) {
        this.data = new int[arr.length + 1];
        capacity = arr.length;
        for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }
        count = arr.length;

        //下面开始整理这个数组成为堆。从最后一个非叶子结点开始调整（因为叶子结点可以看成元素为一的二叉树）
        for (int i = count / 2; i >= 1; i--) { //以1为起始索引的数组构建成完全二叉树时，最后一个叶子结点的1/2就是最后一个非叶子节点
            shiftDown(i);
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int[] getData() {
        return data;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 插入元素
     *
     * @param item
     */
    public void insert(int item) {
        assert (count + 1 <= capacity);//这里是处理数组越界的问题的临时解决方案，如果能完成自动扩容会更好。
        data[++count] = item;
        shiftUp(count);
    }

    /**
     * 插入时堆的调整
     *
     * @param k 位置
     */
    private void shiftUp(int k) {
        while (k > 1 && data[k / 2] < data[k]) {
            swap(data, k / 2, k);
            k /= 2;
        }
    }

    public int extractMax() {
        assert (count > 0);
        int ret = data[1];

        //获取元素之后，总要把最后一个元素放到1的位置
        swap(data, 1, count);
        count--;
        shiftDown(1);

        return ret;
    }

    /**
     * 获取元素之后调整堆。从此元素的两个子节点调整，索引从1开始
     *
     * @param k 开始的位置
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {    //表示此节点一定有孩子
            int j = 2 * k;//此轮循环中，data[k]和data[j]交换位置
            if (j + 1 <= count && data[j + 1] > data[j]) {  //左孩子和右孩子比较，哪个大j就是哪个
                j += 1;
            }
            if (data[k] >= data[j]) { //k节点和他最大的子节点比较，如果k比他们都大，那么就已经满足条件不需要交换了
                break;
            }

            swap(data, k, j);
            k = j;
        }
    }

}