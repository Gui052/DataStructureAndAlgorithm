package Algorithm.Sorts;

/** 插入排序<br/>
 * 每一次不断选择索引位置的元素向前面已经有序的数组插入到合适的位置。
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  0:03
 */
public class InsertSort implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        insertSort(arr, 0, arr.length-1);
    }

    private void insertSort(Comparable[] arr, int l, int r) {
        int sortLength = r - l + 1;
        for (int i = 1 + l; i <= r; i++) { //索引可以从1开始，0和0比较没什么用
            Comparable e = arr[i]; //这里采用记录索引最后赋值，交换的话太慢了
            int j = i;
            for (; j > 0 && e.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }
}
