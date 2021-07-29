package Algorithm.Sorts;

import java.util.Random;

/**
 * 快速排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:42
 */
public class QuickSort<T extends Comparable<T>>
        extends SortHelper<T> implements Sorts<T> {
    Random random = new Random();

    @Override
    public void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(T[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    //这个函数是将数组分为大于小于基准元素的两份
    private int partition(T[] arr, int l, int r) {
        //随机选择一个元素，和第一个交换，可以加快近乎有序的数组的排序时间
        swap(arr, l, random.nextInt() % (r - l + 1) + l);

        T v = arr[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (less(arr[i], v)) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }
}
