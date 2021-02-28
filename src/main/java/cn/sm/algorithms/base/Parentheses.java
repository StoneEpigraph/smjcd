package cn.sm.algorithms.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断字符串中的括号是否对应完整
 */
public class Parentheses {
    public static void main(String[] args) {
        String str = "[()]{}{[()()]()()}";
        System.out.println(AssertComplete(str));
    }

    private static boolean AssertComplete(String str) {
        List<String> res = new ArrayList<String>();
        String[] strArr = str.split("");
        List<String> preStrArr = new ArrayList<String>(3);
        preStrArr.add("(");
        preStrArr.add("[");
        preStrArr.add("{");
        List<String> afterStrArr = new ArrayList<String>(3);
        afterStrArr.add(")");
        afterStrArr.add("]");
        afterStrArr.add("}");
        for (String temp : strArr) {
            if (preStrArr.contains(temp)) {
                res.add(temp);
            } else if (afterStrArr.contains(temp)) {
                // 可以优化, 遇到不合理的直接返回false;
//                System.out.println("pre str: " + temp);
//                System.out.println("after str: " + res.get(res.size() - 1));
//                System.out.println("after str index: " + afterStrArr.indexOf(temp));
//                System.out.println("pre str index: " +  preStrArr.indexOf(res.get(res.size() - 1)));

                if (afterStrArr.indexOf(temp) == preStrArr.indexOf(res.get(res.size() - 1))) {
                    res.remove(res.size() - 1);
                } else {
                    return false;
                }
            }
        }
        if (res.size() > 0) {
            return false;
        }
        return true;
    }
}