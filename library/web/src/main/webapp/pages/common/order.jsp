
<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays orders
  it is possible to delete an order
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">
    <script src="js/bootstrap.min.js" type="javascript"></script>

    <title><fmt:message key="page.order" bundle="${rb}"/></title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="header.jsp" %>
<br>

<%--displays a title of the order--%>
<div id="order_text">
    <fmt:message key="orders" bundle="${rb}"/>
</div>
<br>

<%--display a table with orders--%>
<div id="order_order_table_div">
    <table id="order_order_table">
        <%--name of fields of the table--%>
        <thead>
        <th class="order_table_th"><fmt:message key="book.title" bundle="${rb}"/></th>
        <th class="order_table_th"><fmt:message key="order.status" bundle="${rb}"/></th>
        <c:if test="${sessionScope.readerId != 1}">
            <th class="order_table_th"><fmt:message key="order.action" bundle="${rb}"/></th>
        </c:if>
        <c:if test="${sessionScope.readerId == 1}">
            <th class="order_table_th"><fmt:message key="order.number" bundle="${rb}"/></th>
        </c:if>
        </thead>
        <tbody>

        <%--displays orders of the concrete reader --%>
        <c:forEach var="order" items="${requestScope.orders}">

            <c:if test="${sessionScope.readerId == order.idReader || sessionScope.readerId == 1}">
                <td id="order-table-td">
                <%--displays titles of the books--%>
                    <c:forEach var="book" items="${requestScope.books}">
                        <c:if test="${book.id == order.idBook}">
                        ${book.title}
                        </c:if>
                    </c:forEach>
                </td>

                <%--displays a status of the order (reading hall or lending library)--%>
                <td id="order-table-td">
                    <c:if test="${order.status == 0}">
                        <fmt:message key="lending_library" bundle="${rb}"/>
                    </c:if>
                    <c:if test="${order.status == 1}">
                        <fmt:message key="reading_hall" bundle="${rb}"/>
                    </c:if>
                </td>
                <td id="order-table-td">

                <%--displays the link to delete an order (only for readers)--%>
                    <c:if test="${sessionScope.readerId != 1}">
                        <a href="controller?param=delete_order&id=${order.id}">
                            <img src="image/icon/delete_order_icon.png"
                                 title="<fmt:message key="order.delete" bundle="${rb}"/>"></a>
                    </c:if>
                    <c:if test="${sessionScope.readerId == 1}">
                        ${order.id}
                    </c:if>
                </td>
            </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<br>
<br>

<%--the link to see all books --%>
<div id="show-books-see-all-book">
    <a href="controller?param=take_books" id="href-see-all-books">
        <fmt:message key="href.book.see_all_books" bundle="${rb}"/></a>
</div>

<%--the footer--%>
<%@ include file="footer.jsp" %>

</body>
</html>