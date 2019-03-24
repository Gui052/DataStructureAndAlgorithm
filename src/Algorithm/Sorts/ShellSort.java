package Algorithm.Sorts;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:36
 */
public class ShellSort extends SortHelper implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        shellSort(arr, 0, arr.length - 1);
    }

    private void shellSort(Comparable[] arr, int l, int r) {
        int length = r - l + 1;
        for (int d = length / 2; d > l; d /= 2) {
            for (int i = d; i < length; i++) {
                for (int j = i; j >= d && arr[j].compareTo(arr[j - d]) < 0; j -= d) {
                    swap(arr, j, j - d);
                }
            }
        }
    }
}
