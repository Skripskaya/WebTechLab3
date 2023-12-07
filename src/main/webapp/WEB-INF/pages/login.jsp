<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="login_label"/></title>
</head>
<body>
     <h1><fmt:message key="login_label"/></h1>
     <form:form action="/login/confirm" method="post" modelAttribute="user">
        <input type="hidden" name="command" value="login">
        <label><fmt:message key="login"/></label>
        <form:input type="text" name="login" path="login" required="true"/><br><br>
        <label><fmt:message key="password"/></label>
        <form:input type="password" name="password" path="password" required="true"/><br><br>
         <input type="radio" name="role" value="client" checked><fmt:message key="average_user_label"/><br><br>
         <input type="radio" name="role" value="admin"><fmt:message key="admin_label"/><br><br>
        <button type="submit"><fmt:message key="login_label"/></button>
     </form:form>
     <c:if test="${requestScope.input_error != null}">
        <label class="error"><fmt:message key="input_error"/></label>
     </c:if>
     <c:if test="${requestScope.auth_error != null}">
         <label class="error"><fmt:message key="auth_error"/></label>
     </c:if>
   </body>
</html>