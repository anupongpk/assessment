package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/lotteries")
    public LotteryResponse createLottery(
            @Validated
            @RequestBody LotteryRequest request){
        return lotteryService.createLottery(request);
    }

}
