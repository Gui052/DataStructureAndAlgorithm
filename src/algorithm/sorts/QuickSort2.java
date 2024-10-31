package algorithm.sorts;

import java.util.Random;

/** 快速排序算法改进<br/>
 * 则将两端分别置为大于和小于v的元素，把等于V的均匀分布在其中
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  16:51
 */
public class QuickSort2 extends SortHelper {

    public void sort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }

    private void quickSort2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = partition2(arr, l, r);
        quickSort2(arr, l, p - 1);
        quickSort2(arr, p + 1, r);
    }

    public int partition2(int[] arr, int l, int r) {
        swap(arr,l,new Random().nextInt(r - l + 1) + l);
        int v = arr[l];
        //arr[l+1...i)<v, arr(j....r]>v
        int i=l+1, j=r;
        while (true) {
            while (i <= r && arr[i]<v) {
                i++;
            }
            while (i >= l + 1 && arr[j] > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

}
