package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LotteryResponse {

    private String ticket;
    private List<String> tickets;

    public LotteryResponse(String ticket) {
        this.ticket = ticket;
    }

    public LotteryResponse(List<String> tickets) {
        this.tickets = tickets;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }
}
