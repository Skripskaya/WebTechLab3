package com.bsuir.skripskaya.wt3.lab3.logic;


/**
 * This class represents the list of pages
 * It contains static PageName's instances
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public class PageNames {
    public static final PageName MAIN_PAGE = new PageName("/pages/main.jsp",true);

    public static final PageName ERROR_PAGE = new PageName("/pages/error.jsp",true);

    public static final PageName LOGIN_PAGE = new PageName("/pages/login.jsp",true);

    public static final PageName REGISTER_PAGE = new PageName("/pages/register.jsp",
                                                            true);

    public static final PageName COURSE_CREATION = new PageName(
            "/pages/course/courseCreation.jsp", true);

    public static final PageName COURSE_EDIT = new PageName("/pages/course/courseEdit.jsp",
             true);

    public static final PageName ORDER_ACCEPT = new PageName("/pages/order/orderAccept.jsp",
            true);

    public static final PageName ORDER_CREATION = new PageName("/pages/order/orderCreation.jsp",
            true);
    public static final PageName USER_EDIT = new PageName("pages/user/userEdit.jsp",
            true);
    public static final PageName USER_ORDERS = new PageName("/pages/user/userOrders.jsp",
            true);
    public static final PageName COURSE_VIEW = new PageName("/pages/course/courseView.jsp",
            true);

}
