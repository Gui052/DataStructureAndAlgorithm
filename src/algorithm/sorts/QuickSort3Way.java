package algorithm.sorts;

import java.util.Random;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  16:57
 */
public class QuickSort3Way extends SortHelper {
    public void sort(int[] arr) {
        quickSort3Way(arr, 0, arr.length - 1);
    }

    private void quickSort3Way(int[] arr, int l, int r) { //这里的两个索引是闭区间的，上面的也相同。
        if (l >= r) {
            return;
        }

        //partition
        swap(arr, l, new Random().nextInt(r - l + 1) + l);
        int v = arr[l];

        int lt = l;       //小于v的边界，arr[l+1....lt]<v，一开始保证lt=l（l是v元素故不算在内），此区间没有数据
        int gt = r + 1; //大于v的边界，arr[gt.....r]>v，一开始保证gt在外面，此区间没有数据
        int i = l + 1;  //arr[lt+1....i]==v
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i, ++lt);
                i++;
            } else if (arr[i] > v) {
                swap(arr, i, --gt);
            } else {
                i++;
            }
        }

        swap(arr, l, lt);

        quickSort3Way(arr, l, lt - 1); //因为l和lt交换了，所以这里要减一，维护闭区间
        quickSort3Way(arr, gt, r);
    }

    public static void main(String[] args) {
        QuickSort3Way sorter = new QuickSort3Way();
        int[] arr = new int[]{3, 4, 5, 2, 1, 6, 7, 8, 9};
        sorter.sort(arr);
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }

}
