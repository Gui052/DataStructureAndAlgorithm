package algorithm.sorts;

/**
 * 插入排序<br/>
 * 每一次不断选择索引位置的元素向前面已经有序的数组插入到合适的位置。
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  0:03
 */
public class InsertSort extends SortHelper {

    private void insertSort(int[] arr) {
        int r = arr.length - 1;
        for (int i = 1; i <= r; i++) {
            int e = arr[i]; //这里采用记录索引最后赋值，交换的话太慢了
            int j = i;
            for (; j > 0 && less(e, arr[j - 1]); j--) { //小的往前插
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }
}
