<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/courseEdit.css"/>
<head>
    <title><fmt:message key="edit_course"/></title>
</head>
<body>
    <c:if test="${sessionScope.role == null || !sessionScope.role eq 'admin'}" >
        <c:redirect url="/WEB-INF/pages/error403.jsp"/>
    </c:if>
    <h1><fmt:message key="edit_course" /></h1>
    <form:form method="post" action="/course/updateCourse" accept-charset="UTF-8" modelAttribute="course">

        <label><fmt:message key="course_name" /></label>
        <form:input type="text" path="name" /><br><br>

        <label><fmt:message key="price" /></label>
        <form:input type="number" path="price" /><br><br>

        <label><fmt:message key="author" /></label>
        <form:input type="text" path="author" /><br><br>

        <label><fmt:message key="description" /></label>
        <form:input type="text" path="description" /><br><br>

        <label><fmt:message key="main_tech" /></label>
        <form:input type="text" path="mainTech" /><br><br>

        <button type="submit"><fmt:message key="save_changes"/></button><br><br>

    </form:form>
    <form action="/main" method="get">
        <button type="submit"><fmt:message key="back_button"/></button><br><br>
    </form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error"><fmt:message key="input_error" /></label>
    </c:if>
</body>
</html>