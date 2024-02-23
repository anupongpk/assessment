package com.kbtg.bootcamp.posttest.lottery;

import java.util.List;

public class LotteryResponse {
    private List<String> tickets;

    public LotteryResponse(){

    }

    public List<String> getTickets(){
        return tickets;
    }

    public void setTickets(List<String> tickets){
        this.tickets =tickets;
    }

}
