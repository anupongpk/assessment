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

    List<Lottery> lotteries = new ArrayList<>(
            List.of(
                    new Lottery(1,"100101",80,1),
                    new Lottery(2,"100102",80,1)
            )
    );

    
    public List<String> getLotteries(){
        return lotteries.stream().map(Lottery::getTicket).collect(Collectors.toList());
    }


    public String createLottery(@RequestBody LotteryRequest request){
        // **check ticket already exits
//        lotteries.stream().filter(lottery -> lottery.getTicket().equals(request.ticket()))
//                .findFirst()
//                .ifPresent(lottery -> {
//                    throw new DuplicationException("This ticket " + request.ticket() + " already exits");
//                });


        Optional<Integer> maxId = lotteries.stream().map(Lottery::getId).max(Integer::compareTo);
        int nextId = maxId.orElse(0)+1;

        Lottery lottery = new Lottery(nextId, request.ticket(), request.price(), request.amount());
        lotteries.add(lottery);
        return lottery.getTicket();
    }
    
}
