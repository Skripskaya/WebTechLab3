<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderCreation.css" />
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message  key="edit_profile_label"/></title>
</head>
<body>
<c:if test="${sessionScope.role == null || !sessionScope.role eq 'admin'}" >
    <c:redirect url="/WEB-INF/pages/error403.jsp"/>
</c:if>
<h1><fmt:message  key="edit_profile_label"/></h1>
    <form:form action="/user/updateUser" method="post" modelAttribute="user">

        <label><fmt:message  key="name"/></label>
        <form:input type="text" path="name"  required="true"/><br><br>

        <label><fmt:message  key="surname"/></label>
        <form:input type="text" path="surname"  required="true"/><br><br>

        <label><fmt:message  key="phone"/></label>
        <form:input type="text" path="phoneNumber"  required="true"/><br><br>

        <label><fmt:message  key="email"/></label>
        <form:input type="text" path="email"  required="true"/><br><br>

        <label><fmt:message  key="login"/></label>
        <form:input type="text" path="login"  required="true"/><br><br>

        <button type="submit"><fmt:message  key="save_changes"/></button>
    </form:form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error"><fmt:message  key="input_error"/></label>
    </c:if>
</body>
</html>