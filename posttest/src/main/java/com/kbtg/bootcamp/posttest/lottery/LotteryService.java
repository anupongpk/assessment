package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.exception.InternalServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LotteryService {

    @Autowired
    LotteryRepository lotteryRepository;

    
    public LotteryResponse getLotteries(){
        List<Lottery> lotteries = lotteryRepository.findAll();
        List<String> tickets = lotteries.stream().map(Lottery::getTicket).toList();

        return new LotteryResponse(tickets);
    }

    public Optional<Lottery> getLotteryById(int id){
        return lotteryRepository.findById(Long.valueOf(id));
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
