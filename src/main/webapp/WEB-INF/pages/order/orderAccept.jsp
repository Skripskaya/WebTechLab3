<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderAccept.css" />
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="accept_orders"/></title>
</head>
<body>
    <c:if test="${sessionScope.role == null || !sessionScope.role eq 'admin'}" >
        <c:redirect url="/WEB-INF/pages/error403.jsp"/>
    </c:if>
    <c:if test="${sessionScope.orders != null && !sessionScope.orders.isEmpty()}">
        <c:forEach var="order" items="${sessionScope.orders}">
            <h3><fmt:message key="order_number"/>${order.id}</h3>
            <h4><fmt:message key="creation_date"/>${order.creationDate}</h4>
            <h4><fmt:message key="summary_price"/>${order.summaryPrice}</h4>
            <form action="/order/changeOrderStatus" method="post">
                <c:choose>
                    <c:when test="${order.isAccepted()}">
                        <button type="submit" name="orderId" value="${order.getId()}"><fmt:message key="undo_accept"/></button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" name="orderId" value="${order.getId()}" ><fmt:message key="accept_order"/></button>
                    </c:otherwise>
                </c:choose>
            </form>
            <br><br>

        </c:forEach>
    </c:if>
    <form action="/order/prevAcceptOrdersPage" method="get">
       <c:choose>
           <c:when test="${sessionScope.orders_offset > 0}">
               <button type="submit"><fmt:message key="prev_page"/></button>
           </c:when>
           <c:otherwise>
               <button type="submit" disabled><fmt:message key="prev_page"/></button>
           </c:otherwise>
       </c:choose>
    </form>
    <form action="/order/nextAcceptOrdersPage" method="get">
        <input type="hidden" name="command" value="next_orders_page" />
        <c:choose>
            <c:when test="${sessionScope.orders_offset + 10 > sessionScope.orders_count}">
                <button type="submit" disabled><fmt:message key="next_page"/></button>
            </c:when>
            <c:otherwise>
                <button type="submit"><fmt:message key="next_page"/></button>
            </c:otherwise>
        </c:choose>
    </form>
</body>
</html>