package Interview.LeetCode.dataStructure;

/** 位运算相关
 * @author lan
 * @version 1.0.0
 * @since 2019/3/28  14:04
 *
 * &  与  两个位都为1时，结果才为1
 * |  或  两个位都为0时，结果才为0
 * ^  异或  两个位相同为0，相异为1,相同数得0。两个整数异或，如果大的异或小的相当于减法，反过来相当于加法
 * ~  取反  0变1，1变0
 * << 左移 除以2的n次方，相当于各二进位全部左移若干位，高位丢弃，低位补0
 * >> 右移 乘以2的n次方，各二进位全部右移若干位，对无符号数，高位补0，有符号数，
 *         各编译器处理方法不一样，有的补符号位（算术右移），有的补0（逻辑右移）
 * >>> 为无符号右移，左边会补上 0。
 */
public class BitOption {
    /**
     * NO.461-统计两个数的二进制位 有多少位不同
     * 对两个数进行异或操作，位级表示不同的那一位为 1，统计有多少个 1 即可
     */
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int cnt = 0;
        while (z != 0) {
            if ((z & 1) == 1) {
                cnt++;
            }
            z = z >> 1;
        }
        return cnt;
    }
    //也可以使用Integer来统计
    public int hammingDistance2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * NO.136-给定数组，除了其中一个元素外，每个元素都出现两次。找出只出现一次的元素
     * 两个相同的数异或的结果为 0，对所有数进行异或操作，最后的结果就是单独出现的那个数
     */
    public int singleNumber(int[] nums) {
        int ret = 0;
        for (int num : nums) {
            ret = ret ^ num;       //两个相同的数异或是0
        }
        return ret;
    }

    /**
     * NO.268-从[0...n]的数组中找到遗失的元素
     */
    public int missingNumber(int[] nums) {
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            ret = ret ^ i ^ nums[i];
        }
        return ret ^ nums.length;//length是数组中最大的元素
    }
    //计算式解决方案
    public int missingNumber2(int[] nums) {
        //长度代表数组中最大的数，
        int sum = 0;
        for(int n : nums) sum += n;
        return (nums.length*(nums.length+1)/2)-sum;
    }

    /**
     * NO.260-数组中不重复的两个元素
     * 两个不相等的元素在位级表示上必定会有一位存在不同。
     * 将数组的所有元素异或得到的结果为不存在重复的两个元素异或的结果。
     * diff &= -diff 得到出 diff 最右侧不为 0 的位，
     * <em>也就是不存在重复的两个元素在位级表示上最右侧不同的那一位</em>利用这一位就可以将两个元素区分开来。
     */
    public int[] singleNumber2(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff;
        int[] ret = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                ret[0] ^= num;
            } else {
                ret[1] ^= num;
            }
        }
        return ret;
    }
}
