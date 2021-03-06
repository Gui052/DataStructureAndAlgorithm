package Algorithm.Sorts;

/** 冒泡排序
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:54
 */
public class BubbleSort extends SortHelper implements Sorts{
    @Override
    public void sort(Comparable[] arr) {
        bubbleSort(arr, 0, arr.length - 1);
    }

    private void bubbleSort(Comparable[] arr, int l, int r) {
        int sortLength = r - l + 1;//只需要排序在l和r之间就行了
        for (int i = l; i <= r; i++) {
            boolean flag = false;
            for (int j = l; j < r - i; j++) { //j要小于r-i，因为下面还有j+1
                if (arr[j].compareTo(arr[j + 1]) > 0) {
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
