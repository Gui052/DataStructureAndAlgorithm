package Algorithm.Test;

import Algorithm.Sorts.*;

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
}
