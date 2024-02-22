package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Lottery {
    private final int id;

    @JsonProperty("ticket")
    private String ticket;

    @JsonProperty("price")
    private int price;

    @JsonProperty("amount")
    private int amount;

    public Lottery( int id, String ticket , int price , int amount){
        this.id = id;
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
    }

    public int getId (){
        return id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price){
        this.price = price;
    }


    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

}
