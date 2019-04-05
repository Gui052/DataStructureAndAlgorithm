package Interview;

import Interview.LeetCode.dataStructure.Arrays;
import Interview.LeetCode.dataStructure.BitOption;
import Interview.LeetCode.dataStructure.Hash;

/**
 * @author lan
 * @version 1.0.0
 * @since 2019/3/26  21:43
 */
public class Test {
    public static void main(String[] args) {
        BitOption bitOption = new BitOption();
        int a = bitOption.missingNumber2(new int[]{1, 2, 4, 0});
        System.out.println(a);
    }
}