package cn.sm.algorithms.base;

/**
 * 一些小东西
 */
public class MathDemo {
    public static void main(String[] args) {
        System.out.println(Math.abs(-2147483648));
        System.out.println(1.0 / 0.0);
        System.out.println(Integer.toBinaryString(123));
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(a[i]);
        }
    }
}
