package cn.sm.algorithms.base.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListRefTest {
    public static void testListRef() {
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        List<String> list = new ArrayList();
        int key = 1;
        for (int i = 1; i < 30; i++) {
            if (key != i) {
                res.put(String.valueOf(i), list);
                list = new ArrayList<String>();
                list.add(String.valueOf(i));
            }
        }
        System.out.println(res);
    }

    private static <T> List<List<T>> partitionList(List<T> list, int groupSize) {
        int size = list.size();
        return IntStream.range(0, (size + groupSize - 1) / groupSize)
                .mapToObj(i -> list.subList(i * groupSize, Math.min((i + 1) * groupSize, size)))
                .collect(Collectors.toList());
    }
    public static void splitList2List() {

        // 原始List
        List<String> originalList = IntStream.rangeClosed(1, 23)
                .boxed()
                .map(item -> String.format("item_%s", item))
                .collect(Collectors.toList());

        // 指定的分组大小
        int groupSize = 5;

        // 使用Stream进行List的分组
        List<List<String>> groupedLists = partitionList(originalList, groupSize);

        // 打印结果
        groupedLists.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        testListRef();
        splitList2List();
    }
}
