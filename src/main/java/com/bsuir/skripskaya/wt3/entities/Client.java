package com.bsuir.skripskaya.wt3.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clients")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Client implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cl_id")
    private User user;

    @Column(name="cl_is_banned")
    private boolean isBanned;

    public Client() {
        super();
    }

    public Client(User user,boolean isBanned){
        super();
        this.user = user;
        this.isBanned = isBanned;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }


    public User getUser() {
        return user;
    }


    public void setId(User user) {
        this.user = user;
    }
}
