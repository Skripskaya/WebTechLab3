package com.bsuir.skripskaya.wt3.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Admin implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adm_id")
    private User user;
    @Column(name="adm_is_active",columnDefinition = "BIT(1)")
    private boolean isActive;

    public Admin() {
        super();
    }

    public Admin(User user,boolean isActive){
        super();
        this.user = user;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }
}
