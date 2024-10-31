package algorithm.sorts;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  9:02
 */
public abstract class SortHelper {
    public void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    /**
     * v小于w
     */
    public boolean less(int v, int w) {
        return v < w;
    }
}
