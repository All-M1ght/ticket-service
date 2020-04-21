package com.user.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    @Column(name = "username")
    private String userName;
    public User() {
    }
    public User(Long userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }
    public User(Long userId){
        this.userId = userId;
    }
    public User(String userName){
        this.userName = userName;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
