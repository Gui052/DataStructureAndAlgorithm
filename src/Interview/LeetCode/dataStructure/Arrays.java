package Interview.LeetCode.dataStructure;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/** 数组相关
 * @author lan
 * @version 1.0.0
 * @since 2019/3/26  10:33
 */
public class Arrays {
    /**
     * NO.283-移动数组的0元素到末尾<br/>
     * 思路是先用一个索引，将所有不是0的元素赋值到前面，然后在后面填充0。
     * 也可以用交换，但是不如这个快
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    /**
     * NO.566-更改矩阵维度。输入维度进行重建
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }
        int index = 0;
        int[][] reshapedNums = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                reshapedNums[i][j] = nums[index / n][index % n]; //这个下标很有意思
                index++;
            }
        }
        return reshapedNums;
    }

    /**
     * NO.485 找到数组中连续的1的最大个数。
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                max = Math.max(i - index, max);
            } else {
                index = i;
            }
        }
        return max;
    }

    /**
     * NO.240-从一个二维矩阵中寻找元素<br/> 特点：
     * 每行中的整数按从左到右的升序排序。
     * 每列中的整数按从上到下的顺序排序。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int col = matrix.length - 1;
        int row = 0;
        while (col >= 0 && row < matrix[0].length) {
            if (target < matrix[col][row]) {
                col--;
            } else if (target > matrix[col][row]) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * NO.378-给定一个每行每列有序的数组（元素可能有重复），找到第k个最小的元素。
     * 由于下一行的元素可能比上一行最大的还小，所以从索引上看只可能是近乎有序的一种类型。
     * <br/>
     * 下面通过优先队列实现最大元素优先，如果队列元素少于k，就往里面加，如果队列到k了，而且队列前面元素大于此时
     * 的元素，则出队。这样导致元素少一个，于是再加入一个元素，继续判断，直到结束，此时队列第一个元素就是要找的。
     */
    public int kthSmallest(int[][] matrix, int k) {
        // PriorityQueue是一个由小根堆实现的优先队列，优先队列的作用是能保证每次取出的元素都是队列中权值最小的。
        // 通过后面传入的Collections.reverseOrder实现排序反转，也就是说现在队列里面是最大的优先
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());

        for(int i=0; i<matrix.length; i++) {
            for(int j = 0; j<matrix[i].length; j++) {
                if(queue.size() == k && queue.peek() > matrix[i][j])
                    queue.poll(); //删除堆顶元素
                if(queue.size() < k) //删除之后就满足条件，即可添加。
                    queue.offer(matrix[i][j]);
            }
        }
        return queue.peek();
    }

    /**
     * NO.645 数组由[1...n]元素组成，由于数组中一个元素错误的复制并覆盖了后一个元素，
     * 所以数组中包含两个此元素。需要找到这个元素并把它覆盖的元素一并返回
     */
    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) { //通过将数组元素调换到正确的位置上
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {  //第二个条件就是判断是不是有一个重复的，一旦两个重复的在一起了，就完事儿了。
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * NO.287 在[1...n]数组中找到重复的元素。前提条件是这个数组必须是间隔为1 并且不重复的。
     * 这个算法有点神奇，可能这样的数组就有这样的规律吧
     */
    public int findDuplicate(int[] nums) {
        int l = 1, h = nums.length - 1;
        while (l <= h) {
            int mid = (h - l) / 2 + l;
            int cnt = 0; //记录小于等于mid的个数
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt > mid) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //解法二。双指针解法，类似于有环链表中找出环的入口
    public int findDuplicate2(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * NO.667-数组元素为 1~n 的整数，要求构建数组，使得相邻元素的差值不相同的个数为 k。
     * 让前 k+1 个元素构建出 k 个不相同的差值，序列为：1 k+1 2 k 3 k-1 ... k/2 k/2+1.
     */
    public int[] constructArray(int n, int k) {
        if (k >= n) {
            return null;
        }
        int[] ret = new int[n];
        ret[0] = 1;
        for (int i = 1, interval = k; i <= k; i++, interval--) {
            ret[i] = i % 2 == 1 ? ret[i - 1] + interval : ret[i - 1] - interval; //构造序列，奇偶索引区分
        }
        for (int i = k + 1; i < n; i++) { //这里是处理自然增长的i和k相等以后，后面的直接递增就好了，已经满足k个不等的间隔了
            ret[i] = i + 1;
        }
        return ret;
    }

    /**
     * NO.697-求数组的度。数组的度定义为出现次数最多的元素的个数
     * @param nums
     * @return
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> numsCnt = new HashMap<>();
        Map<Integer, Integer> numsLastIndex = new HashMap<>();
        Map<Integer, Integer> numsFirstIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            numsCnt.put(num, numsCnt.getOrDefault(num, 0) + 1);
            numsLastIndex.put(num, i);
            if (!numsLastIndex.containsKey(num)) {
                numsFirstIndex.put(num, i);
            }
        }
        int maxCnt = 0;
        for (int num : nums) {
            maxCnt = Math.max(maxCnt, numsCnt.get(num));
        }
        int ret = nums.length;
        for (int num : nums) {
            int cnt = numsCnt.get(num);
            if (cnt!=maxCnt) {
                continue;
            }
            ret = Math.min(ret, numsLastIndex.get(num) - numsFirstIndex.get(num) + 1);
        }
        return ret;
    }
}
