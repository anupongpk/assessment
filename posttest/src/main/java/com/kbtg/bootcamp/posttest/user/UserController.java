package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
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


    @GetMapping("/{userId}/lotteries")
    public UserResponse getMyLotteries(@PathVariable("userId") int userId){
        return userService.getMyLotteries(userId);
    }


    @PostMapping("/{userId}/lotteries/{ticketId}")
    public UserTicketResponse buyLottery(
            @PathVariable("userId") int userId,
            @PathVariable("ticketId") int ticketId ){
       return userService.buyLottery(userId, ticketId);
    }


    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public LotteryResponse deleteLottery(
            @PathVariable("userId") int userId,
            @PathVariable("ticketId") int ticketId){
        return userService.deleteLottery(userId, ticketId);
    }
}
