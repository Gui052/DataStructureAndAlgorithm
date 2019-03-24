package DataStructure.Test;

import java.util.Stack;

/** 括号匹配
 * @author lan
 * @version 1.0.0
 * @since 2019/3/23  23:49
 */
public class BracketMatching {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); //从String中获取一个char
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = stack.pop(); //出栈
                //如果当前字符和栈顶不是配对，就不是正确的
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty(); //最后栈为空才算是匹配完整
    }
}
