package Algorithm.Sorts;

/**
 * 冒泡排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:54
 */
public class BubbleSort<T extends Comparable<T>> extends SortHelper<T> implements Sorts<T> {
    @Override
    public void sort(T[] arr) {
        bubbleSort(arr, 0, arr.length - 1);
    }

    private void bubbleSort(T[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            boolean flag = false;
            for (int j = l; j < r - i; j++) {
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
