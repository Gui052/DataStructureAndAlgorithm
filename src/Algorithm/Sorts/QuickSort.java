package Algorithm.Sorts;

/** 快速排序
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  12:42
 */
public class QuickSort extends SortHelper implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Comparable[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition(arr, l, r);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);
    }
    //这个函数是将数组分为大于小于基准元素的两份
    private int partition(Comparable[] arr, int l, int r) {
        //随机选择一个元素，和第一个交换，可以加快近乎有序的数组的排序时间
        swap(arr,l,(int)Math.random()%(r-l+1)+l);

        Comparable v = arr[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                swap(arr, ++j, i);
            }
        }
        swap(arr, l, j);
        return j;
    }
}
