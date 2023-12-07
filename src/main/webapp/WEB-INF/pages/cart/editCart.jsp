<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
<html>
<head>
</head>
<body>
<c:if test="${sessionScope.role == null}" >
    <c:redirect url="/WEB-INF/pages/error403.jsp"/>
</c:if>
<c:choose>
    <c:when test="${sessionScope.cart != null}">
    <c:forEach var="course" items="${sessionScope.cart.getAll()}">
        <section>
            <h3><fmt:message key="name_desc"/> ${course.name}</h3><br><br>
            <h4><fmt:message key="main_tech"/> ${course.mainTech}</h4><br><br>
            <h4><fmt:message key="price"/> ${course.price}</h4><br><br>
            <div>
                <form action="/cart/deleteFromCart" method="post">
                    <button type="submit" name="courseId" value="${course.getId()}"><fmt:message key="delete_from_cart_label"/></button><br><br>
                </form>
            </div>
        </section>
    </c:forEach>
    </c:when>
    <c:otherwise>
        <h2><fmt:message key="no_items_in_cart_label"/></h2>
    </c:otherwise>
</c:choose>
<form action="/main" method="get">
    <button type="submit"><fmt:message key="back_button"/></button><br><br>
</form>
</body>
</html>
