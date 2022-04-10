package interview.leetcode.solution;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 53. 最大子数组和
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int res = nums[0];
        int sun = 0;
        for (int num : nums) {
            if (sun > 0) {
                sun += num;
            } else {
                sun = num;
            }
            res = Math.max(res, sun);
        }
        return res;
    }
}
