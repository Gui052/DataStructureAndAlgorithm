package interview.leetcode.solution;

/**
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 建立一个大根堆，做 k−1 次删除操作后堆顶元素就是我们要找的答案
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        build_maxHeap(nums);
        for (int i = n - 1; i > n - k; i--) {
            swap(nums, 0, i);
            shiftDown(nums, i, 0);  //每一次堆的元素少一个（因为每一次运行最大的都会跑到数组后面成为有序的）
        }
        return nums[0];
    }


    public void build_maxHeap(int[] nums) {
        int n = nums.length;
        //构建大根堆
        for (int i = (n - 1) / 2; i >= 0; i--) {
            shiftDown(nums, n, i);
        }
    }

    /**
     * 调整堆的函数
     * @param n 堆中元素个数
     * @param k 从此节点往下的子节点开始调整
     */
    private void shiftDown(int[] arr, int n, int k) {
        //k的左孩子计算方式
        int j = 2 * k + 1;
        //此轮循环中，data[k]和data[j]交换位置
        while (j < n) { //表示此节点一定有孩子
            if (j + 1 < n && arr[j + 1] > arr[j]) {  //左孩子和右孩子比较，哪个大j就是哪个，左孩子+1就是右孩子
                j += 1;
            }
            if (arr[k] >= arr[j]) { //k节点和他最大的子节点比较，如果k比他们都大，那么就已经满足条件不需要交换了
                break;
            }
            swap(arr, k, j);
            k = j;
            j = 2 * k + 1;
        }
    }
    public void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
