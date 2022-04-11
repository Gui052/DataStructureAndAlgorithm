package algorithm.search;

/** 二分查找法
 * @author lan
 * @version 1.0.0
 * @since 2019/3/20  13:28
 */
public class BinarySearch<T extends Comparable<T>> {

    /**
     * 二分查找
     *
     * @param arr    数组
     * @param n      数组长度
     * @param target 目标
     * @return 如果查找到返回相应索引，否则返回-1
     */
    public int binarySearch(T[] arr, int n, T target) {
        int l = 0;
        int r = n - 1;
        //在arr[l...r]中查找
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }

            //在arr[l....mid-1]查找
            if (target.compareTo(arr[mid]) < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找的递归实现
     * @param arr
     * @param n
     * @param target
     * @return
     */
    public int binarySearchRecursion(T[] arr, int n, T target) {
        return bsRecursion(arr, 0, n - 1, target);
    }
    /**
     * 二分查找的递归实现
     * @param arr    数组
     * @param l      左闭区间
     * @param r      右闭区间
     * @param target 查找元素
     * @return
     */
    private int bsRecursion(T[] arr, int l, int r, T target) {
        int mid = (r - l) / 2 + l;
        if (r < l || mid < 0) {
            return -1;
        }
        if (arr[mid].compareTo(target) == 0) {
            return mid;
        }
        if (arr[mid].compareTo(target) > 0) {
            return bsRecursion(arr, l, mid - 1, target);
        } else {
            return bsRecursion(arr, mid - 1, r, target);
        }
    }

}