<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="register_label"/></title>
</head>
<body>
    <h1><fmt:message key="register_label"/></h1>
    <form:form action="/register/confirm" method="post" modelAttribute="user">
        <label><fmt:message key="name"/></label>
        <form:input path="name" type="text" name="name" required="true" /><br><br>

        <label><fmt:message key="surname"/></label>
        <form:input path="surname" type="text" name="surname" required="true" /><br><br>

         <label><fmt:message key="phone"/></label>
        <form:input path="phoneNumber" type="text" name="phone" required="true"/><br><br>

         <label><fmt:message key="email"/></label>
        <form:input path="email"  type="text" name="email" required="true"/><br><br>

         <label><fmt:message key="login"/></label>
        <form:input path="login"  type="text" required="true"/><br><br>

        <label><fmt:message key="password"/></label>
        <form:input path="password" type="password" required="true"/><br><br>

        <input type="radio" name="role" value="client" checked><fmt:message key="average_user_label"/><br><br>
        <input type="radio" name="role" value="admin"><fmt:message key="admin_label"/><br><br>

        <button type="submit"><fmt:message key="register_button"/></button>
    </form:form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error"><fmt:message key="input_error"/></label>
    </c:if>
    <c:if test="${sessionScope.login_error != null}">
        <label class="error"><fmt:message key="login_error"/></label>
    </c:if>
    <c:if test="${sessionScope.email_error != null}">
        <label class="error"><fmt:message key="email_error"/></label>
    </c:if>
    <c:if test="${sessionScope.phone_error != null}">
        <label class="error"><fmt:message key="phone_error"/></label>
    </c:if>
</body>
</html>