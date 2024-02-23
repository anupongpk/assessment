package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotteryController {

    private final LotteryService lotteryService;

    public LotteryController (LotteryService lotteryService){
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public LotteryResponse getLotteries(){
       return lotteryService.getLotteries();
    }


    @PostMapping("/admin/lotteries")
    public Lottery createLottery(
            @Validated
            @RequestBody LotteryRequest request){
        return lotteryService.createLottery(request);
    }

}
