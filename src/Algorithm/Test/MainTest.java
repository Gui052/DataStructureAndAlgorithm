package Algorithm.Test;

import Algorithm.Sorts.*;
import com.apple.laf.AquaEditorPaneUI;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:58
 */
public class MainTest {
    public static void main(String[] args) {
        Integer[] a = SortTestHelper.generateRandomArray(1000000, 0, 1000);

        Integer[] test1 = a.clone();

        Sorts sorts = new HeapSort();
        SortTestHelper.Test(sorts,test1);

        Integer[] test2 = a.clone();
        Sorts sorts1 = new HeapSortWithArray();
        SortTestHelper.Test(sorts1, test2);
    }

    public void quickSort(int[] array, int l, int r) {
        if (array == null || array.length == 0) {
            return;
        }
        int p = partition(array, l, r);
        quickSort(array, l, p - 1);
        quickSort(array, p + 1, r);
    }

    private int partition(int[] array, int l, int r) {
        int v = array[l];
        int j = l;
        for (int i = l + 1; i < r; i++) {
            if (array[i] > v) {
                swap(array, ++j, i);
            }
        }
        swap(array, j, l);
        return j;
    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
