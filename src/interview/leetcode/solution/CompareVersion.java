package interview.leetcode.solution;

/**
 * 165. 比较版本号
 * https://leetcode-cn.com/problems/compare-version-numbers/
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        int n = version1.length();
        int m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            for (; i < n && version1.charAt(i) != '.'; i++) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            //跳过点号
            ++i;
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; j++) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            ++j;

            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }
}
