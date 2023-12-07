package com.bsuir.skripskaya.wt3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Order implements Serializable {

    @Id
    @Column(name = "ord_id")
    private int id;
    @Column(name = "ord_creation_date")
    private Date creationDate;
    @Column(name = "ord_sum_price")
    private double summaryPrice;

    @Column(name = "ord_is_accepted")
    private boolean isAccepted;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ord_customer")
    private User customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="orders_courses",
    joinColumns = @JoinColumn(name="c_id"),inverseJoinColumns = @JoinColumn(name="ord_id"))
    private List<Course> courses = new ArrayList<>();
    public Order() {

    }

    public Order(int id,Date creationDate, double summaryPrice, boolean isAccepted,User customer) {
        this.id = id;
        this.creationDate = creationDate;
        this.summaryPrice = summaryPrice;
        this.isAccepted = isAccepted;
        this.customer = customer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int orderId) {
        this.id = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
