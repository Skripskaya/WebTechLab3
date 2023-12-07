package com.bsuir.skripskaya.wt3.controller;

import com.bsuir.skripskaya.wt3.entities.Course;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import com.bsuir.skripskaya.wt3.service.Cart;
import com.bsuir.skripskaya.wt3.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CourseService courseService;

    @GetMapping(path="/main")
    public ModelAndView mainPage (@SessionAttribute(name="offset",required = false)Integer offset, HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();

            long courses_count = courseService.getCoursesCount();
            if (offset == null) {
                offset = 0;
            }
            List<Course> courses = courseService.getCoursePageElements(offset, 10);
            WebUtils.setSessionAttribute(request, "offset", offset);
            WebUtils.setSessionAttribute(request, "courses", courses);
            WebUtils.setSessionAttribute(request, "courses_count", courses_count);
            mv.setViewName("/main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:/error");
        }
    }
    @GetMapping(path="/main/prev")
    public ModelAndView previousPage (@SessionAttribute(name="offset")int offset,HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            if (offset > 10) {
                offset -= 10;
            }
            List<Course> courses = courseService.getCoursePageElements(offset, 10);
            WebUtils.setSessionAttribute(request, "offset", offset);
            WebUtils.setSessionAttribute(request, "courses", courses);
            mv.setViewName("redirect:../main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }

    }
    @GetMapping(path="/main/next")
    public ModelAndView nextPage (@SessionAttribute(name="offset")int offset,
                                  @SessionAttribute(name="courses_count")int coursesCount,HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            if(offset + 10 < coursesCount){
                offset += 10;
            }
            List<Course> courses = courseService.getCoursePageElements(offset,10);
            WebUtils.setSessionAttribute(request, "offset", offset);
            WebUtils.setSessionAttribute(request, "courses", courses);
            mv.setViewName("redirect:../main");
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @PostMapping(path="/main/locale")
    public ModelAndView setAppLocale (@RequestParam(name = "locale",required = false)String locale,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        WebUtils.setSessionAttribute(request,"locale",locale);
        WebUtils.setSessionAttribute(request,"lang",locale);
        mv.setViewName("redirect:../main");
        return mv;
    }
    @PostMapping(path ="/main/addToCart")
    public ModelAndView addCourseToCart(@RequestParam(name="courseId")String courseIdString,HttpServletRequest request){
        try {
            ModelAndView mv = new ModelAndView();
            int courseId = Integer.parseInt(courseIdString);
            Course course = courseService.findCourseById(courseId);
            List<Course> courses = (List<Course>) WebUtils.getSessionAttribute(request,"courses");
            Cart userCart;
            if (WebUtils.getSessionAttribute(request, "cart") != null) {
                userCart = (Cart) WebUtils.getSessionAttribute(request, "cart");
            } else {
                userCart = new Cart();
            }
            if (userCart != null) {
                userCart.add(course);
                WebUtils.setSessionAttribute(request, "cart", userCart);
                mv.setViewName("redirect:../main");
            } else {
                mv.setViewName("redirect:../error");
            }
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
}
