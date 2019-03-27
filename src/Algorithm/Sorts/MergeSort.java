package Algorithm.Sorts;

/** 归并排序<br/>
 * @author lan
 * @version 1.0.0
 * @since 2019/3/24  0:04
 */
public class MergeSort extends SortHelper implements Sorts {
    @Override
    public void sort(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    //递归过程
    private void mergeSort(Comparable[] arr, int l, int r) {
        if (l >= r) { //停止递归的判断
            return;
        }
        int mid = (r - l) / 2 + l;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        if (arr[mid].compareTo(arr[mid+1])<=0) {
            return;
        }
        merge(arr, l, mid, r);
    }
    //排序过程
    void merge(Comparable[] arr, int l, int mid, int r) {
        Comparable[] aux = new Comparable[r - l + 1]; //开辟的额外辅助空间
        for (int i = l; i <= r; i++) {
            aux[i - l] = arr[i];
        }

        int i = l;
        int j = mid + 1; //i和j分别是两段的第一个索引
        for (int k = l; k <= r; k++) {
            if (i>mid) {        //说明左边的元素已经没有了，只需要把有边的赋值过去
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {  //右边没有元素了。
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) { //哪个小就放进去，并且对应的索引也递增
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }

    }

}
