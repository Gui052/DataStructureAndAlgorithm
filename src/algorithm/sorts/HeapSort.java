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

    private void heapSort(int[] arr) {
        int r = arr.length - 1;
        MaxHeap maxHeap = new MaxHeap(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }
}
