package interview.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * https://leetcode-cn.com/problems/3sum/
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        //循环遍历，主循环从左到右取值，子循环从组循环位置往后，从两端取值，不从头开始是为了避免重复
        //先进行排序，然后从两头往中间取值
        List<List<Integer>> answer = new ArrayList<>();
        if (nums == null || nums.length <= 2) {
            return answer;
        }
        Arrays.sort(nums);
        //这里考虑到left和right两个指针的取值，需要-2
        for (int i = 0; i < nums.length - 2; i++) {
            //一个数大于0，后面相加不可能小于等于0了
            if (nums[i] > 0) {
                break;
            }
            //排除重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //取负数直接加就行
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //移动指针，不能由元素重复
                    left++;
                    right--;
                    //当前元素与前一个元素判断
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return answer;
    }
}
