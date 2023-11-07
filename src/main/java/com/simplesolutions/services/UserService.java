package com.simplesolutions.services;

import com.simplesolutions.models.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> list = new ArrayList<>();

    public UserService(List<User> list) {
        this.list.add(new User("InMemUser1", "InMemUser1", "InMemUser1@gmail.com", "ROLE_DUMMY"));
        this.list.add(new User("InMemUser2", "InMemUser2", "InMemUser2@gmail.com", "ROLE_DUMMY"));
    }

    public List<User> getAllUsers(){
        return this.list;
    }

    public User getUser(String username) {
        return this.list.stream().filter(user -> user.getUserName().equals(username)).findAny().orElse(null);
    }

    public User addUser(User user) {
        this.list.add(user);
        return user;
    }
}
