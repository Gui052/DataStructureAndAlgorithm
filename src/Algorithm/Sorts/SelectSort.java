package Algorithm.Sorts;

/** 选择排序<br/>
 * 每次从剩余元素中找到最小的元素，和i索引所在位置交换。
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  9:38
 */
public class SelectSort extends SortHelper implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        selectSort(arr, 0, arr.length - 1);
    }

    private void selectSort(Comparable[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            int minIndex = i;
            for (int j = i + 1; j <= r; j++) {
                if (arr[minIndex].compareTo(arr[j])>0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }
}
