package algorithm.sorts;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:36
 */
public class ShellSort extends SortHelper {

    private void shellSort(int[] arr) {
        for (int d = arr.length / 2; d > 0; d /= 2) {
            for (int i = d; i < arr.length; i++) {
                for (int j = i; j >= d && arr[j] <arr[j - d]; j -= d) {
                    swap(arr, j, j - d);
                }
            }
        }
    }

    public static void main(String[] args) {
        ShellSort sorter = new ShellSort();
        int[] arr = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9};
        sorter.shellSort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
