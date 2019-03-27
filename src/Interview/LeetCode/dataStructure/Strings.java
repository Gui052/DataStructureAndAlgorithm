package Interview.LeetCode.dataStructure;

/** 字符串相关
 * @author lan
 * @version 1.0.0
 * @since 2019/3/27  13:37
 */
public class Strings {
    /**
     * NO.242-判断两个字符串中字符是不是相等
     * 可以用 HashMap 来映射字符与出现次数，然后比较两个字符串出现的字符数量是否相同。（同数组697）
     * 由于本题的字符串只包含 26 个小写字符，因此可以使用长度为 26 的整型数组对字符串出现的字符进行统计，不再使用 HashMap
     */
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null) return false;
        if(s.length() != t.length()) return false;
        int[] cnts = new int[26];
        for (char c : s.toCharArray()) {
            cnts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            cnts[c - 'a']--;
        }
        for (int cnt : cnts) {
            if (cnt != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * NO.409-计算一组小写或大写字母组成的字符集合能组成回纹字符串的最大长度
     * 使用长度为 128 的整型数组来统计每个字符出现的个数，每个字符有偶数个可以用来构成回文字符串。
     * 因为回文字符串最中间的那个字符可以单独出现，所以如果有单独的字符就把它放到最中间。
     */
    public int longestPalindrome(String s) {
        int[] cnts = new int[128];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            palindrome += (cnt / 2) * 2; //字符中能取2的倍数的次数
        }
        if (palindrome < s.length()) {
            palindrome++;//这个条件下 s 中一定有单个未使用的字符存在，可以把这个字符放到回文的最中间
        }
        return palindrome;
    }
    //另一种解决方法
    public int longestPalindrome2(String s) {
        int[] arr = new int[128];
        int res = 0;
        for (char c : s.toCharArray()) {
            arr[c]++;
        }
        boolean odd = false;
        for (int i = 0; i < 128; i++) {
            if (arr[i]%2==1) {  //这里是取单个的元素作为中间，只能取一个而已。
                odd = true;
            }
            res += (arr[i] % 2 == 0) ? arr[i] : arr[i] - 1;
        }
        return odd ? res + 1 : res;
    }

    /**
     * NO.205-判断字符串是否同构
     * 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
     * 用两个数组分别记录相同索引下字符出现的次数，如果不相等说明肯定不一样了。
     */
    public boolean isIsomorphic(String s, String t) {
        int[] perIndexOfs = new int[128];
        int[] perIndexOft = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (perIndexOfs[sc] != perIndexOft[tc]) {
                return false;
            }
            perIndexOfs[sc] = i + 1;
            perIndexOft[tc] = i + 1;
        }
        return true;
    }

    /**
     * NO.647-回纹子串的个数
     * 从字符串的某一位开始，尝试着向两边去扩展子字符串。
     */
    private int cnt = 0;
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            extendSubString(s, i, i);           //奇数
            extendSubString(s, i, i + 1);   //偶数
        }
        return cnt;
    }

    private void extendSubString(String s, int start, int stop) {
        while (start >= 0 && stop < s.length() && s.charAt(start) == s.charAt(stop)) {
            start--;
            stop++;
            cnt++;
        }
    }

    /**
     * NO.9-判断数字是不是回纹
     * 将整数分成左右两部分，右边那部分需要转置，然后判断这两部分是否相等。
     */
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;  //将有边的数字转过来
            x /= 10;                      //删掉右边的数
        }
        return x == right || x == right / 10;
    }

    /**
     * NO.696-统计二进制串中0和1个数相等的子串数量
     */
    public int countBinarySubstrings(String s) {
        int perLen = 0;
        int curLen = 1;
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curLen++;
            } else {
                perLen = curLen;
                curLen = 1;
            }
            if (perLen >= curLen) { //这里选择per>cur是per是可控的，在当前情况下不知道cur前面是什么
                count++;
            }
        }
        return count;
    }
}
