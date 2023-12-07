package com.bsuir.skripskaya.wt3.beans;

import com.bsuir.skripskaya.wt3.filters.LocaleFilter;
import com.bsuir.skripskaya.wt3.filters.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class Filters {
    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter(){
        FilterRegistrationBean<SessionFilter> sessionFilterBean
                = new FilterRegistrationBean<>();
        sessionFilterBean.setFilter(new SessionFilter());
        sessionFilterBean.addUrlPatterns("/*");
        sessionFilterBean.setOrder(1);
        return sessionFilterBean;
    }

    @Bean
    public FilterRegistrationBean<LocaleFilter> localeFilter(){
        FilterRegistrationBean<LocaleFilter> localeFilterBean
                = new FilterRegistrationBean<>();
        localeFilterBean.setFilter(new LocaleFilter());
        localeFilterBean.addUrlPatterns("/*");
        localeFilterBean.setOrder(2);
        return localeFilterBean;
    }

}
