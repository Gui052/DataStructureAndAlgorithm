package interview.leetcode.solution;


/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindrome {

    //中心扩展法
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //奇数偶数的子串
            //偶数子串
            int lenEven = expandAroundCenter(s, i, i);
            //奇数子串
            int lenOdd = expandAroundCenter(s, i, i + 1);
            int len = Math.max(lenEven, lenOdd);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 中心扩展核心，从中间向两边扩展
     * @return 返回最长长度
     */
    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //因为相同之后left和right是下次比较的位置，所以left+1和right-1才是真正的子串起始位置，所以计算方式如下
        //回文串的长度是right - 1 - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}
