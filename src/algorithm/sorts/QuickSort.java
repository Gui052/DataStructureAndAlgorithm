package algorithm.sorts;

import java.util.Random;

/**
 * 快速排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:42
 */
public class QuickSort extends SortHelper {

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }

    //这个函数是将数组分为大于小于基准元素的两份
    private int partition(int[] arr, int l, int r) {
        //随机选择一个元素，和第一个交换，可以加快近乎有序的数组的排序时间
        swap(arr, l, new Random().nextInt(r - l + 1) + l);

        int v = arr[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (less(arr[i], v)) {
                //在基准元素之后的位置进行交换
                swap(arr, ++j, i);
            }
        }
        //最后将基准元素放到位置上
        swap(arr, l, j);
        return j;
    }
}
