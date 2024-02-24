package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketRepository;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private UserTicketRepository userTicketRepository;

    public List<User> getUsers (){
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById((long) id)
                .orElseThrow(()-> new NotFoundException("This user id :" + id +" not found"));
    }

    public UserTicketResponse buyTicket(int userId, int ticketId) {
        getUserById(userId);
        Lottery lottery = lotteryRepository.findById((long) ticketId)
                .orElseThrow(()-> new NotFoundException("This lottery id :" + ticketId +" not found"));

        UserTicket userTicket = new UserTicket(userId, ticketId, 1 ,lottery.getPrice());
        UserTicket saveUserTicket = userTicketRepository.save(userTicket);

        return new UserTicketResponse(saveUserTicket.getId());
    }
}
