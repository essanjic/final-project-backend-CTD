package com.group6.cenapp.model;


import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;


@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time arrival_time;
    private LocalDate check_in_date;
    private LocalDate checkout_date;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Reservation() {
    }

    public Reservation(Integer id, Time arrival_time, LocalDate check_in_date, LocalDate checkout_date, String comments, Food food, User user) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.check_in_date = check_in_date;
        this.checkout_date = checkout_date;
        this.comments = comments;
        this.food = food;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(LocalDate checkout_date) {
        this.checkout_date = checkout_date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Food getProduct() {
        return food;
    }

    public void setProduct(Food food) {
        this.food = food;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
