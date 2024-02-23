package com.kbtg.bootcamp.posttest.users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> getUsers (){
        return userRepository.findAll();
    }


    public User getUserById(int id) {
        return null;
    }

}
