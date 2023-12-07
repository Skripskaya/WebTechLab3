package com.bsuir.skripskaya.wt3.controller;


import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.entities.Order;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import com.bsuir.skripskaya.wt3.service.Cart;
import com.bsuir.skripskaya.wt3.service.CourseService;
import com.bsuir.skripskaya.wt3.service.OrderService;
import com.bsuir.skripskaya.wt3.service.UserService;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class RedirectController {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/")
    public ModelAndView loadApp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/main");
        return mv;
    }

    @GetMapping(path="/login")
    public ModelAndView redirectToLoginPage () {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",new User());
        mv.setViewName("login");
        return mv;
    }
    @GetMapping(path="/register")
    public ModelAndView redirectToRegisterPage () {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",new User());
        mv.setViewName("register");
        return mv;
    }
    @GetMapping(path="/user/userEditPage")
    public ModelAndView redirectToUserEditPage (HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            sessionFactory.openSession();
            String userLogin = String.valueOf(WebUtils.getSessionAttribute(request, "login"));
            User logInUser = userService.findUserByLogin(userLogin);
            WebUtils.setSessionAttribute(request,"userId",logInUser.getId());
            WebUtils.setSessionAttribute(request,"userPass",logInUser.getPassword());
            mv.addObject("user",logInUser);
            mv.setViewName("user/userEdit");
            sessionFactory.getCurrentSession().close();
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @GetMapping(path="/user/userOrdersPage")
    public ModelAndView redirectToUserOrdersPage (HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            String userLogin = String.valueOf(WebUtils.getSessionAttribute(request, "login"));
            User logInUser = userService.findUserByLogin(userLogin);
            List<Order> userOrders =  orderService.getOrdersByUser(logInUser);
            for(Order order : userOrders){
                order.setCourses(courseService.getCourseByOrder(order));
            }
            WebUtils.setSessionAttribute(request,"orders",userOrders);
            mv.setViewName("user/userOrders");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @GetMapping(path="/order/orderCreationPage")
    public ModelAndView redirectToOrderCreationPage (HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Cart userCart = (Cart)WebUtils.getSessionAttribute(request,"cart");
        if(userCart != null) {
            WebUtils.setSessionAttribute(request, "chosen", userCart.getAll());
        }
        mv.setViewName("order/orderCreation");
        return mv;
    }

    @GetMapping(path="/course/courseEditPage")
    public ModelAndView redirectToCourseEditPage (@RequestParam(name="courseId")String courseIdString,
                                                  HttpServletRequest request) {
        try {
            int courseId = Integer.parseInt(courseIdString);
            ModelAndView mv = new ModelAndView();
            Course editableCourse = courseService.findCourseById(courseId);
            mv.addObject("course",editableCourse);
            WebUtils.setSessionAttribute(request,"courseId",courseId);
            mv.setViewName("/course/courseEdit");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @GetMapping(path="/course/showCourse")
    public ModelAndView redirectToShowCoursePage (@RequestParam(name="courseId")String courseIdString) {
        try {
            int courseId = Integer.parseInt(courseIdString);
            ModelAndView mv = new ModelAndView();
            Course showingCourse = courseService.findCourseById(courseId);
            mv.addObject("course",showingCourse);
            mv.setViewName("/course/courseView");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @GetMapping(path="/order/orderAcceptPage")
    public ModelAndView redirectToOrderAcceptPage (HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            long ordersCount = orderService.getOrdersCount();
            List<Order> orders = orderService.getOrdersPageElements(0,10);
            WebUtils.setSessionAttribute(request,"orders_offset",0);
            WebUtils.setSessionAttribute(request,"orders_count",ordersCount);
            WebUtils.setSessionAttribute(request,"orders",orders);
            mv.setViewName("/order/orderAccept");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @GetMapping(path="/course/courseCreationPage")
    public ModelAndView redirectToCourseCreationPage (HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("course",new Course());
        mv.setViewName("course/courseCreation");
        return mv;
    }
    @GetMapping(path="/cart/cartEditPage")
    public ModelAndView redirectToCartEditPage (HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("course",new Course());
        mv.setViewName("cart/editCart");
        return mv;
    }
}
