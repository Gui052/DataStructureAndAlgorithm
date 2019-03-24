package Algorithm.Test;

import Algorithm.Sorts.*;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:58
 */
public class MainTest {
    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 6, 3, 8, 1, 8, 4, 3, 2};
        Sorts bubbleSort = new MergeSortBU();
        bubbleSort.sort(a);

        SortTestHelper.print(a);
    }
}
