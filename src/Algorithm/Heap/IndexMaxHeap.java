package Algorithm.Heap;

/** 索引堆<br/>
 * indexes表示当data变成堆时他们在数组中的位置，indexes[i]=10则代表i这个位置的真实数据是data[10]<br/>
 * reveres数组则是反向查找，reveres的索引是indexes的数据，reverse的数据是indexes的索引。<br/>
 * @author lan
 * @version 1.0.0
 * @since 2019/3/19  21:31
 */
public class IndexMaxHeap<T extends Comparable<T>> {
    private int[] indexes;
    private int[] reverse;
    private T[] data;
    /**
     * 数量
     */
    private int count;
    /**
     * 容量
     */
    private int capacity;

    public IndexMaxHeap(int capacity) {
        this.data = (T[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        /*for (int i = 0; i <= capacity; i++) {
            reverse[i]=0;
        }*/
        count = 0;
        this.capacity = capacity;
    }

    public IndexMaxHeap(T[] arr) {
        this.data = (T[]) new Comparable[arr.length+1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        capacity = arr.length;
        /*for (int i = 0; i < arr.length; i++) {
            data[i + 1] = arr[i];
        }*/
        System.arraycopy(data,1,arr,0,arr.length);
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

    public T[] getData() {
        return data;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 插入元素
     * @param item
     */
    public void insert(int i,T item) {
        assert (count + 1 <= capacity);
        assert (i + 1 >= 1 && i + 1 <= capacity);

        i += 1; //从1索引
        data[i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;

        count++;
        shiftUp(count);
    }

    /**
     * 插入时堆的调整
     * @param k 位置
     */
    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]].compareTo(data[indexes[k]]) < 0) {
            swap(indexes, k / 2, k);
            reverse[indexes[k / 2]] = k / 2; //反过来修改，上面是根据索引改值，这里是根据值改索引（从indexes的角度说）
            reverse[indexes[k]] = k;
            k /= 2;
        }
    }

    /**
     * 获取堆顶元素
     * @retur
     */
    public T extractMax() {
        assert (count > 0);
        T ret = data[indexes[1]];

        //获取元素之后，总要把最后一个元素放到1的位置
        swap(indexes, 1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0; //这里是因为下面的count--是删除元素，所以这里就直接赋值为0就行
        count--;
        shiftDown(1);

        return ret;
    }

    /**
     * 获取元素之后调整堆。从此元素的两个子节点调整，索引从1开始
     * @param k 开始的位置
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {    //表示此节点一定有孩子
            int j = 2 * k;//此轮循环中，data[k]和data[j]交换位置
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) > 0) {  //左孩子和右孩子比较，哪个大j就是哪个
                j += 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) >= 0) { //k节点和他最大的子节点比较，如果k比他们都大，那么就已经满足条件不需要交换了
                break;
            }

            swap(indexes, k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }

    /**
     * 获取堆顶元素的索引
     * @return
     */
    public int extractMaxIndex() {
        assert (count > 0);
        int ret = indexes[1] - 1; //转成0开始的索引

        //获取元素之后，总要把最后一个元素放到1的位置
        swap(indexes, 1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0;
        count--;
        shiftDown(1);

        return ret;
    }

    /**
     * 获取特定位置元素
     * @param i
     * @return
     */
    public T getItem(int i) {
        assert (contain(i));
        return data[i + 1];
    }

    /**
     * 改变特定位置数组元素
     * @param i
     * @param newItem
     */
    public void change(int i, T newItem) {
        assert (contain(i));
        i += 1;
        data[i] = newItem;
        //更新indexes堆
        //找到indexes[j] = i ，表示data[i]在堆中的位置
        //之后进行shiftUp和shiftDown
        int j = reverse[i];
        shiftUp(j);
        shiftDown(j);
    }

    private boolean contain(int i) {
        assert (i + 1 >= 1 && i + 1 <= capacity);
        return reverse[i + 1] != 0;
    }

}

