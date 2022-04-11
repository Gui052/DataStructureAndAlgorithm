package algorithm.sorts;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:36
 */
public class ShellSort<T extends Comparable<T>> extends SortHelper<T> implements Sorts<T> {
    @Override
    public void sort(T[] arr) {
        shellSort(arr, 0, arr.length - 1);
    }

    private void shellSort(T[] arr, int l, int r) {
        int length = r - l + 1;
        for (int d = length / 2; d > l; d /= 2) {
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && less(arr[j], arr[j - d]); j -= d) {
                    swap(arr, j, j - d);
                }
            }
        }
    }
}
