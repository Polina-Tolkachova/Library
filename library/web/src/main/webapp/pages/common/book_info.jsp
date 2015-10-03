<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays information about concrete book
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.book_info" bundle="${rb}" /></title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="header.jsp" %>
<br>
<br>
        <%--displays the information about a book--%>
        <div id="open-book-div">
                <table id="open-book-table">
                    <tr class="open_book_table_tr">
                        <%--the title--%>
                        <td class="open_book_table_td">
                            <fmt:message key="book.title" bundle="${rb}"/>
                        </td>
                        <td class="open_book_table_td">${requestScope.book.title}</td>
                    </tr>
                    <%--the author--%>
                    <tr class="open_book_table_tr">
                        <td class="open_book_table_td">
                        <fmt:message key="book.author" bundle="${rb}"/></td>
                        <td class="open_book_table_td"> ${requestScope.author.name} &nbsp &nbsp ${requestScope.author.surname}
                    </td>
                    <%--the category--%>
                    <tr class="open_book_table_tr">
                        <td td class="open_book_table_td">
                            <fmt:message key="book.category" bundle="${rb}"/></td>
                        <td class="open_book_table_td">${category}</td>
                    </tr>
                </table>
        </div>
<br>
<br>

     <%--the link to see all books --%>
    <div id="open_see_all_book_div">
        <a href="controller?param=take_books" id="href-see-all-books">
            <fmt:message key="href.book.see_all_books" bundle="${rb}"/></a>
    </div>
<br>
<br>
<br>
<br>

<%--the footer--%>
<%@ include file="footer.jsp" %>

</body>
</html>