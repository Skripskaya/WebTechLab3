package com.bsuir.skripskaya.wt3.controller;

import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import com.bsuir.skripskaya.wt3.service.CourseService;
import com.bsuir.skripskaya.wt3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CreateController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CourseService courseService;

    @PostMapping(path="/order/createOrder")
    public ModelAndView finalizeOrder(@SessionAttribute(name="chosen") List<Course> courses,
                                      @SessionAttribute(name="login")String login){
        try {
            ModelAndView mv = new ModelAndView();
            orderService.createOrder(courses,login);
            mv.setViewName("redirect:/main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }

    @PostMapping(path="/course/createCourse")
    public ModelAndView createCourse(@ModelAttribute(name="course")Course course){
        try {
            ModelAndView mv = new ModelAndView();
            courseService.createCourse(course);
            mv.setViewName("redirect:/main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
}
