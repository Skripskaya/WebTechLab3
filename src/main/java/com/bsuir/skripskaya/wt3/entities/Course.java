package com.bsuir.skripskaya.wt3.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id")
    private int id;
    @Column(name="c_name")
    private String name;
    @Column(name="c_price")
    private double price;
    @Column(name="c_author")
    private String author;
    @Column(name="c_description")
    private String description;
    @Column(name="c_main_tech")
    private String mainTech;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="orders_courses",
            joinColumns = @JoinColumn(name="ord_id"),
            inverseJoinColumns = @JoinColumn(name="c_id"))
    private List<Order> orders = new ArrayList<>();

    public Course() {

    }

    public Course(String courseName, double coursePrice, String courseAuthor, String description, String mainTech) {
        this.name = courseName;
        this.price = coursePrice;
        this.author = courseAuthor;
        this.description = description;
        this.mainTech = mainTech;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainTech() {
        return mainTech;
    }

    public void setMainTech(String mainTech) {
        this.mainTech = mainTech;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id
                && Double.compare(course.price, price) == 0
                && name.equals(course.name)
                && author.equals(course.author)
                && description.equals(course.description)
                && mainTech.equals(course.mainTech);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, author, description, mainTech, orders);
    }
}

