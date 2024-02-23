package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.exception.DuplicationException;
import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotteryService {

    LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository){
        this.lotteryRepository = lotteryRepository;
    }

    
    public List<Lottery> getLotteries(){
        return lotteryRepository.findAll();
    }


    public Lottery createLottery(@RequestBody LotteryRequest request){
        Lottery lottery = new Lottery();

        lottery.setTicket(request.ticket());
        lottery.setPrice(request.price());
        lottery.setAmount(request.amount());

        lotteryRepository.save(lottery);

        return lottery;
    }
    
}
