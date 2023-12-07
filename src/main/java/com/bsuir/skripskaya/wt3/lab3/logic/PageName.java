package com.bsuir.skripskaya.wt3.lab3.logic;


/**
 * This class represents the page with name and redirect method
 * It contains name of page and condition for availability by sendRedirect() or forward()
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public class PageName {

    private String name;

    private boolean isRedirect;

    public PageName(){

    }

    public PageName(String name,boolean isRedirect){
        this.name = name;
        this.isRedirect = isRedirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }
}
