package Algorithm.Sorts;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  9:02
 */
public class SortHelper<T>{
    public void swap(T[] arr, int from, int to) {
        T temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
