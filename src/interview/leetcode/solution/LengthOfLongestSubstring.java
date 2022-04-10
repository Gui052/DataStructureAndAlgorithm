package interview.leetcode.solution;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        //map保存的是字符串不重复的位置
        Map<Character, Integer> map = new HashMap<>();
        //滑动窗口的start位置
        int index = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (map.containsKey(tmp) && map.get(tmp) > index) {
                index = map.get(tmp);
            }
            length = Math.max(length, i - index + 1);
            map.put(tmp, i + 1);
        }
        return length;
    }
}
