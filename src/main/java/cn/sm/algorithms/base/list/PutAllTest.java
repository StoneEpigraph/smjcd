package cn.sm.algorithms.base.list;

import java.util.ArrayList;
import java.util.List;

public class PutAllTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        List<String> subList = new ArrayList<>();

        subList.add("1");
        subList.add("2");
        subList.add("3");
        subList.add("4");
        subList.add("5");

        list.addAll(subList);

        subList.remove("1");
        subList.stream().forEach(item -> {
            System.out.println(item);
        });

        System.out.println("================");

        list.stream().forEach(item -> {
            System.out.println(item);
        });
    }
}
