package com.bsuir.skripskaya.wt3.controller;

import com.bsuir.skripskaya.wt3.entities.Admin;
import com.bsuir.skripskaya.wt3.entities.Client;
import com.bsuir.skripskaya.wt3.entities.User;
import com.bsuir.skripskaya.wt3.exceptions.ServiceException;
import com.bsuir.skripskaya.wt3.service.AdminService;
import com.bsuir.skripskaya.wt3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClientService clientService;

    @PostMapping(path="/register/confirm")
    public ModelAndView registerUser(@ModelAttribute User user, @RequestParam(name = "role")String role,
                                     HttpServletRequest request) throws ServiceException {
        try {
            ModelAndView mv = new ModelAndView();
            if (role.equals("admin")) {
                adminService.registerAdmin(user);
                WebUtils.setSessionAttribute(request,"login",user.getLogin());
                WebUtils.setSessionAttribute(request,"role","admin");
                mv.setViewName("redirect:/main");
            } else {
                clientService.registerClient(user);
                WebUtils.setSessionAttribute(request,"login",user.getLogin());
                WebUtils.setSessionAttribute(request,"role","client");
                mv.setViewName("redirect:/main");
            }
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:error");
        }
    }
    @PostMapping(path="/login/confirm")
    public ModelAndView loginUser(@ModelAttribute User user,@RequestParam(name = "role")String role,
                                  HttpServletRequest request) {
        try {
            ModelAndView mv = new ModelAndView();
            if (role.equals("admin")) {
                Admin logInAdmin = adminService.loginAdmin(user);
                if(logInAdmin != null) {
                    WebUtils.setSessionAttribute(request, "login", user.getLogin());
                    WebUtils.setSessionAttribute(request, "role", "admin");
                    mv.setViewName("redirect:../main");
                }else {
                    request.setAttribute("auth_error",true);
                    mv.setViewName("/login");
                }
            } else {
                Client logInClient = clientService.loginClient(user);
                if(logInClient != null) {
                    WebUtils.setSessionAttribute(request,"login",user.getLogin());
                    WebUtils.setSessionAttribute(request,"role","client");
                    mv.setViewName("redirect:../main");
                }else {
                    request.setAttribute("auth_error",true);
                    mv.setViewName("/login");
                }
            }
            return mv;
        }catch (ServiceException e){
            return new ModelAndView("redirect:../error");
        }
    }
    @PostMapping(path="/logout")
    public ModelAndView logOutUser(HttpServletRequest request) throws ServiceException {
        ModelAndView mv = new ModelAndView();
        WebUtils.setSessionAttribute(request,"login",null);
        WebUtils.setSessionAttribute(request,"role",null);
        mv.setViewName("redirect:/main");
        return mv;
    }
}
