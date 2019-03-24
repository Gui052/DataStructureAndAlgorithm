package Algorithm.Test;

import Algorithm.Sorts.Sorts;

import java.util.Random;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  9:10
 */
public class SortTestHelper {
    /**
     * 生成随机数组
     * @param n 长度
     * @param rangeL 左范围
     * @param rangeR 右范围
     * @return 数组
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert (rangeL <= rangeR);
        Integer[] arr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;  //这里生成范围在rangeL和rangeR范围内
        }
        return arr;
    }

    /**
     * 打印数组函数
     * @param arr
     */
    public static void print(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i % 50 == 0) {
                System.out.println();
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void Test(Sorts sorts, Comparable[] arr) {

        long startTime = System.nanoTime();

        sorts.sort(arr);

        long endTime = System.nanoTime();

        System.out.println((endTime - startTime) / 1000000000.0);
    }
}
