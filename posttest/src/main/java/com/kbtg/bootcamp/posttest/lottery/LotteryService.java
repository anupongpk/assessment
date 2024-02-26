package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LotteryService {

    private final LotteryRepository lotteryRepository;


    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    
    public LotteryResponse getLotteries(){
        List<Lottery> lotteries = lotteryRepository.findAll();
        List<String> tickets = lotteries.stream().map(Lottery::getTicket).toList();

        return new LotteryResponse(tickets);
    }

    @Transactional
    public LotteryResponse createLottery(@RequestBody LotteryRequest request){
        Lottery lottery = new Lottery();

        lottery.setTicket(request.ticket());
        lottery.setPrice(request.price());
        lottery.setAmount(request.amount());

        try{
            lotteryRepository.save(lottery);
            return new LotteryResponse(lottery.getTicket());
        }catch (Exception e){
            throw new InternalServiceException("Error creating lottery. Please try again later.");
        }

    }
    
}
