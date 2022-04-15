package interview.leetcode.solution;

/**
 * 10进制转二进制
 */
public class TransToBinary {

    //无符号右移
    public void transformToBinary1(int decimal){
        for(int i = 31;i >= 0; i--)
            System.out.print(decimal >>> i & 1);
    }

    //除基倒取余法
    public void transformToBinary2(int decimal) {
        StringBuffer res = new StringBuffer();
        change(10, res);
        System.out.println(res);
    }

    public void change(int num, StringBuffer buffer) {
        if (num == 0) {
            return;
        }
        change(num / 2, buffer);
        buffer.append(num % 2);
    }

    public void changeLoop(int num, StringBuffer buffer) {
        while (num > 0) {
            buffer.append(num % 2);
            num = num / 2;
        }
        buffer.reverse();
    }

}
