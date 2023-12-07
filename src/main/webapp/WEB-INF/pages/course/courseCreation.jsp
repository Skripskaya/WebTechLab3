<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/courseEdit.css"/>
<meta charset="UTF-8">
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="course_creation_label"/></title>
</head>
<body>
    <c:if test="${sessionScope.role == null || !sessionScope.role eq 'admin'}" >
        <c:redirect url="/WEB-INF/pages/error403.jsp"/>
    </c:if>
    <h1><fmt:message key="course_creation_label"/></h1>
    <form:form action="/course/createCourse" method="post" accept-charset="UTF-8" modelAttribute="course" >
        <label><fmt:message key="course_name"/></label>
        <form:input type="text" path="name" required="true" /><br><br>

        <label><fmt:message key="author"/></label>
        <form:input type="text" path="author" required="true" /><br><br>

        <label><fmt:message key="main_tech"/></label>
        <form:input type="text" path="mainTech" required="true" /><br><br>

        <label><fmt:message key="price"/></label>
        <form:input type="number" min="0" path="price" required="true" /><br><br>

        <label><fmt:message key="description"/></label>
        <form:textarea path="description" rows="4" cols="50" required = "true"></form:textarea><br><br>

        <button type="submit"><fmt:message key="create_course"/></button>
    </form:form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error"><fmt:message key="input_error"/></label>
    </c:if>
</body>
</html>