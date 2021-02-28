package cn.sm.algorithms.base;

/**
 * 求最大公约数
 */
public class Gcd {

    public static void main(String[] args) throws Exception{
        System.out.println(gcd(110454, 12));
        System.out.println(Math.E);
        System.out.println(Math.PI);
        System.out.println(gcd(-1232, 123));
    }

    /**
     * 欧几里得求最大公约数
     * @param m
     * @param n
     * @return
     * @throws Exception
     */
    public static int gcd(int m, int n) throws Exception{
        if (m < 0 || n < 0) {
            throw new Exception("算数不能小于0");
        }
        if (m < n) {
            int temp = n;
            n = m;
            m = temp;
        }
        if (n == 0) {
            return m;
        }
        int r = m % n;
        return gcd(n, r);
    }
}
