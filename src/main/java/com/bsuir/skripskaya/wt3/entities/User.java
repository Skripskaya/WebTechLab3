package com.bsuir.skripskaya.wt3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    @Id
    @Column(name="u_id")
    private int id;
    @Column(name="u_name")
    private String name;
    @Column(name="u_surname")
    private String surname;

    @Column(name="u_phone_num")
    private String phoneNumber;

    @Column(name="u_email")
    private String email;
    @Column(name="u_login")
    private String login;
    @Column(name="u_pass_hash")
    private String password;

    @Column(name="u_reg_date")
    private Date registrationDate;

    @OneToMany(targetEntity = Order.class,mappedBy = "customer",fetch = FetchType.EAGER)
    public List<Order> orders = new ArrayList<>();

    public User(){

    }

    public User(int id,String name,String surname,String phoneNumber,String email,
                String login,String password,Date registrationDate){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
