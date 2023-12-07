package com.bsuir.skripskaya.wt3.lab3.controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.SQLException;


/**
 * The Controller servlet is responsible for handling all requests to the
 * application. It initializes the database connection pool, processes
 * requests from the client, and forwards them to the appropriate
 * command handler.
 *
 * @author Fedor
 * @version 1.0
 * @since 2023-11-27
 */

@Controller
public class MainController {
    @RequestMapping(value = {"/main","/"}, method = RequestMethod.GET)
    public String doGet(Model model){
        return "index";
    }
}
