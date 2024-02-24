package com.kbtg.bootcamp.posttest.userTicket;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user_ticket")
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Integer userId;

    @NotNull
    private int lottery_id;

    @NotNull
    private int quantity;

    @NotNull
    private int price;

    public UserTicket(int userId, int lottery_id, int quantity, int price) {
        this.userId = userId;
        this.lottery_id = lottery_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getLottery_id() {
        return lottery_id;
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

    public void setLottery_id(int lottery_id) {
        this.lottery_id = lottery_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}