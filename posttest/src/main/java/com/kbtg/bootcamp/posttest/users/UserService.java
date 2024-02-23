package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    List<User> users = new ArrayList<>(
           List.of(
                   new User(1001011110, "user1"),
                   new User(1001011111, "user2"),
                   new User(1001011112, "user3")
           )
    );


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    public List<User> getUsers (){
//        try{
//            callService();
//        }catch (Exception e){
//            throw new InternalServiceException("Internal Service exception with User service");
//        }
//        return users;
        return userRepository.findAll();
    }


    public User getUserById(int id) {
        return users.stream().filter(user->user.getId() == id)
                .findFirst()
                .orElseThrow(()-> new NotFoundException("User not found by id " + id));
    }


//    public void callService(){
//        throw new RuntimeException();
//    }
}
