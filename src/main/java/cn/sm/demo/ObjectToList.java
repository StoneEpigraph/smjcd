package cn.sm.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

/**
 * @ClassName ObjectToList
 * @Description TODO
 * @Author StoneEpigraph
 * @Date 10/8/22 11:30 AM
 * @Version 1.0
 **/
public class ObjectToList {

    static class User {
        private String username;

        private int age;

        public String getUsername() {
            return this.username;
        }

        public User(String username) {
            this.username = username;
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        User user1 = new User("stone");
        User user2 = new User("lisi");

        users.add(user1);
        users.add(user2);

        String[] objects = users.stream().map(item -> item.getUsername()).toArray(String[]::new);

        System.out.println(Arrays.toString(objects));
    }
}
