package com.kbtg.bootcamp.posttest.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    List<User> users = new ArrayList<>(
           List.of(
                   new User(1001011110),
                   new User(1001011111),
                   new User(1001011112)
           )
    );

    public List<User> getUsers (){
        return users;
    }


    public Optional<User> getUserById(int id) {
        return users.stream().filter(user->user.getId() == id).findFirst();
    }
}
