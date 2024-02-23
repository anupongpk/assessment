package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "lottery")
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=6 , max=6)
    private String ticket;

    @NotNull
    @Min(value = 80)
    private int price;

    @NotNull
    @Min(value = 1)
    private int amount;

    public Lottery(){

    }

    public Lottery(@NotNull(message = "Ticket is required.") @Size(min = 6, max = 6, message = "Ticket must be 6 digits.") String ticket, @Min(value = 80, message = "Price must be at least 80.") int price, @Min(value = 1, message = "Amount must be at least 1") int amount){

    }

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
