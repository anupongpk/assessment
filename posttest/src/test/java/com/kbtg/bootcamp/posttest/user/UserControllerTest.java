package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.userTicket.UserTicketResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp(){
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /users should return user list")
    void viewUsers() throws Exception {

        User user1 = new User(1234567890,"user1");
        User user2 = new User(1234567890,"user2");

        when(userService.getUsers()).thenReturn(List.of(user1,user2));

        mockMvc.perform(get("/users"))
                .andExpect(jsonPath("$[0].username", is("user1")))
                .andExpect(jsonPath("$[1].username", is("user2")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when perform GET request with user ID on GET: /users/{userId} should return user details")
    void viewUserById() throws Exception {
        int userId = 1234567890;
        User user = new User(userId, "user1");

        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(jsonPath("$.id", is(1234567890)))
                .andExpect(jsonPath("$.username", is("user1")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when buy lottery on POST: /users/{userId}/lotteries/{ticketId} should return user ticket id from table user_ticket")
    void butLottery() throws Exception {
        int userTicketId = 1;
        int userId = 1234567890;
        int ticketId = 3;

        UserTicketResponse userTicketResponse = new UserTicketResponse(userTicketId);

        when(userService.buyLottery(userId,ticketId)).thenReturn(userTicketResponse);

        mockMvc.perform(post("/users/{userTicketId}/lotteries/{ticketId}", userId, ticketId))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("when get user lotteries on GET: /{userId}/lotteries should return user lotteries details")
    void viewUserLotteries() throws Exception {
        int userId = 1234567890;
        List<String> tickets = List.of("111222", "333444");
        int count = 2;
        int cost = 160;

        UserResponse userResponse = new UserResponse(tickets, count, cost);

        when(userService.getMyLotteries(userId)).thenReturn(userResponse);

        mockMvc.perform(get("/users/{userId}/lotteries", userId))
                .andExpect(jsonPath("$.tickets[0]",is("111222")))
                .andExpect(jsonPath("$.tickets[1]",is("333444")))
                .andExpect(jsonPath("$.count",is(2)))
                .andExpect(jsonPath("$.cost",is(160)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("when deleting a lottery on DELETE: /{userId}/lotteries/{ticketId} should return the ticket number")
    void deleteLottery() throws Exception {
        int userId = 1234567890;
        int ticketId = 3;

        LotteryResponse lotteryResponse = new LotteryResponse("123456");

        when(userService.deleteLottery(userId, ticketId)).thenReturn(lotteryResponse);

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticketId}", userId, ticketId))
                .andExpect(jsonPath("$.ticket",is("123456")))
                .andExpect(status().isOk());
    }
}