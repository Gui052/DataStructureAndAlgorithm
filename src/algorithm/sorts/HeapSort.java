package algorithm.sorts;

import algorithm.heap.MaxHeap;

/**
 * 使用链表大根堆的堆排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  17:04
 */
public class HeapSort extends SortHelper {

    private void sort(int[] arr) {
        MaxHeap maxHeap = new MaxHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void main(String[] args) {
        HeapSort sorter = new HeapSort();
        int[] arr = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9};
        sorter.sort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
