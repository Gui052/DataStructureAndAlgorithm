package Algorithm.Sorts;

import static java.lang.Math.*;

/** 自底向上的归并排序<br/>
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  10:03
 */
public class MergeSortBU<T extends Comparable<T>>
        extends MergeSort<T> implements Sorts<T> {
    @Override
    public void sort(T[] arr) {
        int n = arr.length;
        for (int size = 1; size <= n; size += size) {  //size是每次分割的长度，从1开始是自底向上
            for (int j = 0; j + size < n; j += size + size) {
                merge(arr, j, size + j - 1, min(j + 2 * size - 1, n - 1));
            }
        }
    }
}
