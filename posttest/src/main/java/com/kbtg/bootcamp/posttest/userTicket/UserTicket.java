package com.kbtg.bootcamp.posttest.userTicket;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="user_ticket")
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Integer userId;

    @NotNull
    private int ticketId;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    public UserTicket(){

    }

    public UserTicket(int userId, int ticketId, int quantity, int price) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
