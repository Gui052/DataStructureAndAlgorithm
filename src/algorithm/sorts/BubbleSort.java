package algorithm.sorts;

/**
 * 冒泡排序
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:54
 */
public class BubbleSort extends SortHelper {

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1; j++) {
                //慢慢的把最大的元素推到最后面
                if (arr[j + 1] < arr[j]) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort sorter = new BubbleSort();
        int[] arr = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9};
        sorter.sort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
