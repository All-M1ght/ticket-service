package com.ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "state")
    private Integer state;
    @Column(name = "price")
    private Integer price;

    public Ticket() {

    }
    public Ticket(Long id, Integer state) {
        this.id = id;
        this.state = state;
    }
    public Ticket(Long id, Integer state,Integer price) {
        this.id = id;
        this.state = state;
        this.price = price;
    }

    public Ticket(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
