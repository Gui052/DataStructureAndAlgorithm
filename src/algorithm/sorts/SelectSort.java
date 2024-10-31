package algorithm.sorts;

/**
 * 选择排序<br/>
 * 每次从剩余元素中找到最小的元素，和i索引所在位置交换。
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  9:38
 */
public class SelectSort extends SortHelper {

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            //找到剩余部分最小值索引
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        SelectSort sorter = new SelectSort();
        int[] arr = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9};
        sorter.sort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}
