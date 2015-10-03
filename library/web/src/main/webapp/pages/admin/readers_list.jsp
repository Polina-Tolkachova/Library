
<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays information about readers
  it is possible to band and lift the ban of a reader
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.reader_list" bundle="${rb}" /></title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="../common/header.jsp"%>
<br>
<br>

<div id="reader-list-table-div">
    <table id="reader-list-table">

        <th id="reader-list-table-th">
            <fmt:message key="reader.name" bundle="${rb}" /></th>
        <th id="reader-list-table-th">
            <fmt:message key="reader.surname" bundle="${rb}" /></th>
        <th id="reader-list-table-th">
            <fmt:message key="reader.mail" bundle="${rb}" /></th>
        <th id="reader-list-table-th">
            <fmt:message key="reader.decision" bundle="${rb}" /></th>

        <c:forEach var="reader" items="${requestScope.readers}">
            <tr>
                <td class="reader_list_table_td">${reader.name}</td>
                <td class="reader_list_table_td">${reader.surname}</td>
                <td class="reader_list_table_td">${reader.mail}</td>
                <td class="reader_list_table_td"><c:choose>
                    <%--(1 - banned, 0 - no banned)--%>
                    <c:when test="${reader.banned == 1 and reader.id != 1}">
                        <%--changes a ban status of a reader--%>
                        <a href="/library/controller?param=change_ban_status&id=${reader.id}"
                        id="reader-list-href-lift-ban">
                        <img src="image/icon/lift_ban_icon.png" title="<fmt:message key="title.lift_ban"
                        bundle="${rb}"/>">
                    </c:when>
                    <c:when test="${reader.banned == 0 and reader.id != 1}">
                        <%--changes a ban status of a reader--%>
                        <a href="/library/controller?param=change_ban_status&id=${reader.id}"
                        id="reader-list-href-ban"> <img src="image/icon/ban_icon.png"
                            title="<fmt:message key="title.under_ban" bundle="${rb}"/>">
                    </c:when>
                </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%--the link to see all books --%>
<div id="show-books-see-all-book">
    <br> <br> <a href="controller?param=take_books" id="href-see-all-books">
    <fmt:message key="href.book.see_all_books" bundle="${rb}" /></a>
</div>

<%--the footer--%>
<%@ include file="../common/footer.jsp"%>

</body>
</html>