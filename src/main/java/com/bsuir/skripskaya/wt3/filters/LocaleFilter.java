package com.bsuir.skripskaya.wt3.filters;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(2)
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if(httpRequest.getSession().isNew()){
            httpRequest.setAttribute("lang","ru");
        }
        chain.doFilter(request, response);
    }
}
