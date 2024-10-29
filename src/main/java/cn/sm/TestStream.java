package cn.sm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStream {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.stream().forEach(item -> {
            System.out.println(item);
            System.out.println(item);
        });
    }

}
