package com.bsuir.skripskaya.wt3.controller;

import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.entities.Order;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import com.bsuir.skripskaya.wt3.service.Cart;
import com.bsuir.skripskaya.wt3.service.CourseService;
import com.bsuir.skripskaya.wt3.service.OrderService;
import com.bsuir.skripskaya.wt3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UpdateController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping(path = "/course/updateCourse")
    public ModelAndView editCourse(@ModelAttribute(name="course")Course course, HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            int courseId = Integer.parseInt(String.valueOf(WebUtils.getSessionAttribute(request,"courseId")));
            course.setId(courseId);
            courseService.updateCourse(course);
            WebUtils.setSessionAttribute(request,"courseId",null);
            mv.setViewName("redirect:../main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @PostMapping(path = "/user/updateUser")
    public ModelAndView editUser(@ModelAttribute(name="user") User user, HttpServletRequest request){
        try {
            ModelAndView mv = new ModelAndView();
            int userId = Integer.parseInt(String.valueOf(WebUtils.getSessionAttribute(request,"userId")));
            String userPass = String.valueOf(WebUtils.getSessionAttribute(request,"userPass"));
            user.setId(userId);
            user.setPassword(userPass);
            WebUtils.setSessionAttribute(request,"userId",null);
            WebUtils.setSessionAttribute(request,"userPass",null);
            userService.updateUser(user);
            mv.setViewName("redirect:../main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @PostMapping(path = "/order/changeOrderStatus")
    public ModelAndView changeOrderStatus(@ModelAttribute(name="orderId")String orderId, HttpServletRequest request){
        try {
            ModelAndView mv = new ModelAndView();
            Order order = orderService.findOrderById(Integer.parseInt(orderId));
            order.setAccepted(!order.isAccepted());
            orderService.updateOrder(order);
            mv.setViewName("redirect:/order/orderAcceptPage");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @PostMapping(path = "/cart/deleteFromCart")
    public ModelAndView deleteElementFromCart(@RequestParam(name="courseId")String courseId, HttpServletRequest request){
        try {
            ModelAndView mv = new ModelAndView();
            Cart userCart = (Cart)WebUtils.getSessionAttribute(request,"cart");
            if(userCart != null) {
                Course course = courseService.findCourseById(Integer.parseInt(courseId));
                userCart.remove(course);
                WebUtils.setSessionAttribute(request,"cart",userCart);
            }
            mv.setViewName("redirect:/cart/editCart");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
}
