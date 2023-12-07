<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<html>
<header class="header">
    <div>
        <form action="/main/locale" method="post">
            <button type="submit" name="locale" value="en">EN</button>
        </form>
        <form action="/main/locale" method="post">
            <button type="submit" name="locale" value="ru">RU</button>
        </form>
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <h3><fmt:message key="greeting"/> ${sessionScope.login}</h3>
                <form action="/logout" method="post">
                    <button type="submit"><fmt:message key="logout"/></button>
                </form>
                <form action="/user/userEditPage" method="get">
                    <button type = "submit"><fmt:message key="edit_profile"/></button>
                </form>
                <form action="/user/userOrdersPage" method="get">
                    <input type="hidden" name="command" value="form_my_orders">
                    <button type="submit"><fmt:message key="user_orders"/></button>
                </form>
                <form action="/order/orderCreationPage" method="get">
                  <button type="submit"><fmt:message key="finalize_order"/></button><br/>
                </form>
                <form action="/cart/cartEditPage" method="get">
                    <button type="submit"><fmt:message key="edit_cart"/></button><br/>
                </form>
                 <c:if test="${sessionScope.role eq 'admin'}">
                     <form action="/order/orderAcceptPage" method="get">
                         <button type = "submit"><fmt:message key="accept_orders"/></button>
                     </form>
                     <form action="/course/courseCreationPage" method="get">
                         <button type="submit"><fmt:message key="create_course"/></button>
                     </form>
                 </c:if>
            </c:when>
            <c:otherwise>
               <form action="/login" method="get">
                    <button type="submit"><fmt:message key="login_label"/></button>
                </form>
                <form action="/register" method="get">
                    <button type = "submit"><fmt:message key="reg"/></button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
</header>
<c:forEach var="course" items="${sessionScope.courses}" >
    <section>
            <h3><fmt:message key="name_desc"/> ${course.name}</h3>
            <h4><fmt:message key="main_tech_desc"/> ${course.mainTech}</h4>
            <h4><fmt:message key="price_desc"/> ${course.price}</h4>
            <div>
            <c:if test="${sessionScope.role eq 'admin'}">
                <form action="/course/courseEditPage" method="get">
                    <button type="submit" name="courseId" value="${course.getId()}"><fmt:message key="edit_course"/></button>
                </form>
            </c:if>
            <form action="/main/addToCart" method="post">
                <c:if test="${sessionScope.role != null}">
                <c:choose>
                    <c:when test="${sessionScope.cart.contains(course)}">
                        <button type="submit" name="courseId" value="${course.getId()}" disabled><fmt:message key="already_in_cart"/></button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="courseId" value="${course.getId()}" ><fmt:message key="add_course_to_cart"/></button>
                    </c:otherwise>
                </c:choose>
                </c:if>
            </form>
            <form action="/course/showCourse" method="get">
                <button type = "submit" name="courseId" value="${course.getId()}"><fmt:message key="show_course"/></button>
            </form>
             </div>
            <h4><fmt:message key="author_desc"/> ${course.author}</h4>
    </section>
</c:forEach>
<div>
<form action="/main/prev" method="post">
    <c:choose>
        <c:when test="${sessionScope.offset < 10}">
            <button type="submit" disabled><fmt:message key="prev_page"/></button>
        </c:when>
        <c:otherwise>
            <button type="submit"><fmt:message key="prev_page"/></button>
        </c:otherwise>
    </c:choose>
</form>

<form action="/main/next" method="post">
    <c:choose>
    <c:when test="${sessionScope.offset + 10 >= sessionScope.courses_count}">
        <button type="submit" disabled><fmt:message key="next_page"/></button>
    </c:when>
    <c:otherwise>
        <button type="submit"><fmt:message key="next_page"/></button>
    </c:otherwise>
    </c:choose>
</form>
</div>
</html>