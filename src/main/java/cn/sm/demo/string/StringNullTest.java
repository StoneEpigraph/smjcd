package cn.sm.demo.string;

public class StringNullTest {

    public static void main(String[] args) {
        Object str = null;
        System.out.println(String.valueOf(str).trim());
        System.out.println("20210101".compareTo("20201231"));

        System.out.println("123" + null);
        System.out.println("123".length());
//        System.out.println("123".substring(0, 100));
    }
}
