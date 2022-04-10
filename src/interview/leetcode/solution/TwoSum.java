package interview.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * 1. 两数之和
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        //用map保存元素和元素的索引位置，如果目标和当前的差值在map中，直接取这个差值的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
