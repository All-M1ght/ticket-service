package com.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "uid")
    private Long uid;
    @Column(name = "tid")
    private Long tid;
    @Column(name = "state")
    private Integer state;
    @Column(name = "price")
    private Integer price;

    public Order() {

    }
    public Order(Long id,Long uid, Long tid, Integer state) {
        this.id = id;
        this.uid = uid;
        this.tid = tid;
        this.state = state;
    }
    public Order(Long uid, Long tid, Integer state,Integer price) {
        this.uid = uid;
        this.tid = tid;
        this.state = state;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
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
