package cn.sm.algorithms.base;

public class ByteTest {
    public static void main(String[] args) {
        short body = 17183;
        int body2 = body << 22;
        //System.out.println((short)body2);
        //System.out.println(body2);
        short body3 = (short) (body2 >>> 22);
        System.out.println(body3);
        System.out.println(String.format("body: %s, body2: %s, body3: %B", body, body2, body3));


        short ebody = 17183;
        int ebody2 = (int) (body << 6);
        int ebody3 = (int) (body2 >>> 6);
        System.out.println(String.format("ebody: %s, ebody2: %s, ebody3: %s", ebody, ebody2, ebody3));

    }
}
