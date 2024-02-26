package com.kbtg.bootcamp.posttest.user;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Integer id;

    private String username;

    public User(){

    }

    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String username) {
        this.username = username;
    }

}
