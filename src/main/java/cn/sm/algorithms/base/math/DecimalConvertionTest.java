package cn.sm.algorithms.base.math;

public class DecimalConvertionTest {
    public static void main(String[] args) {
        int i = Integer.valueOf("00000f837", 16) / 10;
        System.out.println(i);
        System.out.println("Integer Max Value: " + Integer.MAX_VALUE);
        System.out.println("Integer Min Value: " + Integer.MIN_VALUE);
        System.out.println("Long Max Value: " + Long.MAX_VALUE);
        System.out.println("Long Min Value: " + Long.MIN_VALUE);

    }
}
