package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public UserTicketResponse buyTicket(
            @PathVariable int userId,
            @PathVariable int ticketId ){
       return userService.buyTicket(userId, ticketId);
    }

}
