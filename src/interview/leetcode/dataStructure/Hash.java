package interview.leetcode.dataStructure;

import java.util.HashMap;
import java.util.HashSet;

/** 哈希表相关
 * 哈希表使用 O(N) 空间复杂度存储数据，并且以 O(1) 时间复杂度求解问题。
 *
 * Java 中的 HashSet 用于存储一个集合，可以查找元素是否在集合中。如果元素有穷，并且范围不大，那么可以用一个布尔数组来存储一个元素是否存在。
 * 例如对于只有小写字符的元素，就可以用一个长度为 26 的布尔数组来存储一个字符集合，使得空间复杂度降低为 O(1)。
 *
 * Java 中的 HashMap 主要用于映射关系，从而把两个元素联系起来。HashMap 也可以用来对元素进行计数统计，此时键为元素，值为计数。
 * 和 HashSet 类似，如果元素有穷并且范围不大，可以用整型数组来进行统计。在对一个内容进行压缩或者其它转换时，利
 * 用 HashMap 可以把原始内容和转换后的内容联系起来。例如在一个简化 url 的系统中 Leetcdoe : 535. Encode and Decode TinyURL (Medium)，利
 * 用 HashMap 就可以存储精简后的 url 到原始 url 的映射，使得不仅可以显示简化的 url，也可以根据简化的 url 得到原始 url 从而定位到正确的资源。
 *
 * @author lan
 * @version 1.0.0
 * @since 2019/3/27  16:35
 */
public class Hash {
    /**
     * NO.1-数组中两个数的和为给定值
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexForNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexForNum.containsKey(target - nums[i])) {
                return new int[]{indexForNum.get(target - nums[i]), i};
            } else {
                indexForNum.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * NO.217p-判断数组中是否有重复元素
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() < nums.length;
    }

    /**
     * NO.594-找到最长和谐序列
     * 定义和谐序列的最大值和最小值之间的差值正好是1,不需要保证这些元素都排在一起
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }
        int count = 0;
        for(int i: map.keySet()){
            if(map.containsKey(i+1)){
                count = Math.max(map.get(i) + map.get(i + 1), count);
            }
        }
        return count;
    }
}
