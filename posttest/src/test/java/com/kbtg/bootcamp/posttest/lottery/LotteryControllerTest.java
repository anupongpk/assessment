package com.kbtg.bootcamp.posttest.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LotteryControllerTest {

    MockMvc mockMvc;

    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp(){
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /lotteries should return lottery list")
    void viewLotteries() throws Exception {

        List<String> expectedTickets = List.of("123456", "112233");
        LotteryResponse lotteryResponse = new LotteryResponse(expectedTickets);

        when(lotteryService.getLotteries()).thenReturn(lotteryResponse);

        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$.tickets[0]", is("123456")))
                .andExpect(jsonPath("$.tickets[1]", is("112233")))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("when admin create lottery on perform on POST: /admin/lotteries should return body contain lottery detail")
    void createLottery() throws Exception {
        Lottery lottery = new Lottery();

        lottery.setTicket("111222");
        lottery.setPrice(80);
        lottery.setAmount(1);

        LotteryResponse lotteryResponse = new LotteryResponse(lottery.getTicket());

        when(lotteryService.createLottery(any())).thenReturn(lotteryResponse);

        String requestBody = "{\"ticket\":\"111222\",\"price\":80,\"amount\":1}";

        mockMvc.perform(post("/admin/lotteries")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("111222")));
    }

}