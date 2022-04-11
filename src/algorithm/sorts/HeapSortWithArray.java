package algorithm.sorts;

/** 使用数组构建大根堆的堆排序
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  17:10
 */
public class HeapSortWithArray<T extends Comparable<T>>
        extends SortHelper<T> implements Sorts<T> {
    @Override
    public void sort(T[] arr) {
        heapSortWithArray(arr);
    }

    public void heapSortWithArray(T[] arr) {
        int n = arr.length;
        //构建大根堆
        for (int i = (n - 1) / 2; i >= 0; i--) {
            shiftDown(arr, n, i);
        }

        //开始从堆顶和最后一个元素交换，实现排序
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);  //每一次堆的元素少一个（因为每一次运行最大的都会跑到数组后面成为有序的）
        }
    }

    /**
     * 索引从0开始的调整堆的函数
     * @param arr
     * @param n
     * @param k
     */
    private void shiftDown(T[] arr, int n, int k) {
        int j = 2 * k + 1;//此轮循环中，data[k]和data[j]交换位置
        while (j < n) { //表示此节点一定有孩子
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {  //左孩子和右孩子比较，哪个大j就是哪个
                j += 1;
            }
            if (arr[k].compareTo(arr[j]) >= 0) { //k节点和他最大的子节点比较，如果k比他们都大，那么就已经满足条件不需要交换了
                break;
            }
            swap(arr, k, j);
            k = j;
            j = 2 * k + 1;
        }
    }

}
