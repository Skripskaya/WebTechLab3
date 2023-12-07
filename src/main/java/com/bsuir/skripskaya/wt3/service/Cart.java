package com.bsuir.skripskaya.wt3.service;



import com.bsuir.skripskaya.wt3.entities.Course;

import java.util.ArrayList;
import java.util.List;



public class Cart {
    private List<Course> courses = new ArrayList<>();

    public Cart() {

    }

    public boolean add(Course course){
        return courses.add(course);
    }

    public boolean remove(Course course){
        return courses.remove(course);
    }

    public void clear(){
        courses.clear();
    }

    public boolean contains(Course course){
        return courses.contains(course);
    }

    public List<Course> getAll() {
        return courses;
    }

    public boolean isEmpty() {
        return courses.isEmpty();
    }
}
