package algorithm.sorts;

/**
 * 冒泡排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:54
 */
public class BubbleSort extends SortHelper {

    private void bubbleSort(int[] arr) {
        for (int i = 0; i <= arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i; j++) {
                //慢慢的把最大的元素推到最后面
                if (less(arr[j + 1], arr[j])) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
