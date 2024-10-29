package cn.sm.algorithms.base.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> needSaveMacIdList = Arrays.asList(1,2,3,4);
        int batchSearchTaosCount = 3;

        List<List<Integer>> macIdListList = IntStream.range(0, (needSaveMacIdList.size() + batchSearchTaosCount - 1) / batchSearchTaosCount)
                .mapToObj(i -> needSaveMacIdList.subList(i * batchSearchTaosCount, Math.min((i + 1) * batchSearchTaosCount, needSaveMacIdList.size())))
                .collect(Collectors.toList());
        System.out.println(macIdListList);
    }
}
