
<%--
Created by Polina Tolkachyova.
Date: 01.08.2015

displays links depend on a role of the user
--%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include
        file="/pages/admin/include_language.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title>
        <fmt:message key="page.header" bundle="${rb}" />
    </title>
</head>
<body>
<div id="header">

    <table>
        <tr>
            <br>
            <%--displays information depend on a role of the user--%>
            <div id="header-buttons">
                <c:choose>
                    <%--2 - user--%>
                    <c:when test="${sessionScope.reader.role == 2}">
                        <%--the link to login page--%>
                        <a href="/library/controller?param=login_logout&action=logout"
                           id="header-logout-button">
                            <fmt:message key="title.logout" bundle="${rb}" />
                        </a>
                        <br>
                        <br>
                    </c:when>
                    <%--1 - admin--%>
                    <c:when test="${sessionScope.reader.role == 1}">
                        <%--the link to login page--%>
                        <a href="/library/controller?param=login_logout&action=logout"
                           id="header-logout-button">
                            <fmt:message key="title.logout" bundle="${rb}" />
                        </a>
                        <br>
                        <br>
                        <br>
                        <div id="header-button">

                            <a href="/library/controller?param=path_update_book_page"
                               id="header-add-book-button">
                                <fmt:message key="book.add" bundle="${rb}" />
                            </a>
                            &nbsp &nbsp
                            <%--the link to black list--%>
                            <a href="/library/controller?param=reader_list"
                               id="header-add-black-list-button">
                                <fmt:message key="href.black_list" bundle="${rb}" />
                            </a>
                        </div>
                    </c:when>

                </c:choose>
            </div>
        </tr>
        <tr>
            <br>
            <%--displays concrete greeting of the reader--%>
            <div id="header-greeting">
                <c:choose>
                    <c:when test="${sessionScope.reader != null}">
                        <fmt:message key="text.reader_greeting" bundle="${rb}" />
                        ${sessionScope.reader.name}
                    </c:when>
                </c:choose>
            </div>
        </tr>
    </table>
</div>
</body>
</html>