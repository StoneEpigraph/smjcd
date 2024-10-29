import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestListRef {

    @Test
    public void testListRef() {
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
}
