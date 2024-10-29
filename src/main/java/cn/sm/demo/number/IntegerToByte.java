package cn.sm.demo.number;

public class IntegerToByte {
    public static void main(String[] args) {
        int num = 123;
        byte b = (byte) num;
        System.out.println(b);
        b = new Integer(num).byteValue();
        System.out.println(b);
    }
}
