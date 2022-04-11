package algorithm.sorts;

import algorithm.heap.MaxHeap;

/** 使用链表大根堆的堆排序
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  17:04
 */
public class HeapSort extends SortHelper implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        heapSort(arr, 0, arr.length - 1);
    }

    private <T extends Comparable<T>> void  heapSort(T[] arr, int l, int r) {
        MaxHeap<T> maxHeap = new MaxHeap<T>(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }
}
