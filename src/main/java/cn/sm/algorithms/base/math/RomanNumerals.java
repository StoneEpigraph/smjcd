package cn.sm.algorithms.base.math;

/**
 * 与罗马数字相关
 */
public class RomanNumerals {

    public static int toNumber(String romanStr) {
        if (romanStr == null || "".equals(romanStr.trim())) {
            return 0;
        }
        int sum = 0;
        int preNum = getValue(romanStr.charAt(0));
        for(int i = 1; i < romanStr.length(); i++) {
            int num = getValue(romanStr.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    /**
     * 将单个罗马字符转成数字
     * @param romanChar
     * @return
     */
    public static int getValue(char romanChar) {
        switch (romanChar) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        String romanStr = "XVI";
        System.out.println(toNumber(romanStr));
    }
}


