package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.userTicket.UserTicket;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketRepository;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LotteryRepository lotteryRepository;
    @Autowired
    private UserTicketRepository userTicketRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById((long) id)
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException("This user id : " + id + " not found"));
    }


    public UserResponse getMyLotteries(int userId) {
        getUserById(userId);
        List<UserTicket> userTickets = userTicketRepository.findByUserId(userId);

        List<String> tickets = new ArrayList<>();
        List<Integer> lotteryIds = userTickets.stream().map(UserTicket::getTicketId).toList();

        lotteryIds.forEach(lotteryId -> {
            Optional<Lottery> lottery = lotteryRepository
                    .findById(Long.valueOf(lotteryId))
                    .stream().findFirst();

            if (lottery.isPresent()) {
                tickets.add(lottery.get().getTicket());
            }
        });

        int count = calculateCount(userTickets);
        int cost = calculateCost(userTickets);

        return new UserResponse(tickets, count, cost);
    }

    @Transactional
    public UserTicketResponse buyLottery(int userId, int ticketId) {
        getUserById(userId);
        Lottery lottery = lotteryRepository.findById((long) ticketId)
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException("This lottery id : " + ticketId + " not found"));

        UserTicket userTicket = new UserTicket(userId, ticketId, 1, lottery.getPrice());
        UserTicket saveUserTicket = userTicketRepository.save(userTicket);

        return new UserTicketResponse(saveUserTicket.getId());
    }

    private int calculateCount(List<UserTicket> userTickets) {
        return userTickets.stream().mapToInt(userTicket -> {
            Optional<Lottery> lottery = lotteryRepository
                    .findById(Long.valueOf(userTicket.getTicketId()))
                    .stream().findFirst();

            if (lottery.isPresent()) {
                return lottery.get().getAmount();
            }
            return 0;
        }).sum();
    }

    private int calculateCost(List<UserTicket> userTickets) {
        return userTickets.stream().mapToInt(userTicket -> {
            Optional<Lottery> lottery = lotteryRepository.findById(Long.valueOf(userTicket.getTicketId()))
                    .stream().findFirst();

            if (lottery.isPresent()) {
                return lottery.get().getPrice();
            }
            return 0;
        }).sum();
    }

    @Transactional
    public LotteryResponse deleteLottery(int userId, int ticketId){
        getUserById(userId);
        Optional<UserTicket> userTicket = Optional.ofNullable(userTicketRepository.findByUserId(userId)
                .stream()
                .filter(ut -> ut.getTicketId() == ticketId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("This lottery id : " + ticketId + " not found")));

        if(userTicket.isPresent()){
            String ticket = lotteryRepository.findById((long) ticketId).stream().findFirst().get().getTicket();

            userTicketRepository.delete(userTicket.get());
            return new LotteryResponse(ticket);
        }
        throw new NotFoundException("This lottery id :" + ticketId + " not found");
    }

}