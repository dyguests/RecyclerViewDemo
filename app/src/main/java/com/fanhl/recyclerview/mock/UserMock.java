package com.fanhl.recyclerview.mock;

import com.fanhl.recyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 16/4/20.
 */
public class UserMock {
    private static User item(int i) {
        return new User("Name" + i);
    }

    public static List<User> list() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(item(i));
        }
        return list;
    }
}
