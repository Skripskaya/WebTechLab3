<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="UTF-8">
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/courseEdit.css"/>
<head>
    <title>Изменение курса</title>
</head>
<body>
    <c:if test="${sessionScope.role == null || !sessionScope.role eq 'admin'}" >
        <c:redirect url="/pages/error403.jsp"/>
    </c:if>
    <h1>Изменение курса</h1>
    <form method="post" action="/main" accept-charset="UTF-8">
        <input type="hidden" name="command" value="course_edit">
        <label for="courseName">Название курса:</label>
        <input type="text" id="courseName" name="c_name" value="${course.name}"><br><br>

        <label for="coursePrice">Цена курса:</label>
        <input type="text" id="coursePrice" name="c_price" value="${course.price}"><br><br>

        <label for="courseAuthor">Автор курса:</label>
        <input type="text" id="courseAuthor" name="c_author" value="${course.author}"><br><br>

        <label for="courseDescription">Описание курса:</label>
        <textarea id="courseDescription" name="c_description">${course.description}</textarea><br><br>

        <label for="courseMainTech">Основные технологии:</label>
        <input type="text" id="courseMainTech" name="c_main_tech" value="${course.mainTech}"><br><br>
        <button type="submit">Сохранить</button><br><br>

    </form>
    <form action="/main" method="post">
        <input type="hidden" name="command" value="redirect"/>
        <input type="hidden" name="redirect" value="true"/>
        <input type="hidden" name="page" value="/pages/main.jsp"/>
        <button type="submit">Назад</button><br><br>
    </form>
    <c:if test="${sessionScope.input_error != null}">
        <label class="error">Введенные данные для обновления некорректны!</label>
    </c:if>
</body>
</html>