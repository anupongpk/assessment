package com.kbtg.bootcamp.posttest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getUsers (){
        return userService.getUsers();
    }


    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") int id){
        return  userService.getUserById(id);
    }

}
