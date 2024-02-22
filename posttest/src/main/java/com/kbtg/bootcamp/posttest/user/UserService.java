package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
//        try{
//            callService();
//        }catch (Exception e){
//            throw new InternalServiceException("Internal Service exception with User service");
//        }
        return users;
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
