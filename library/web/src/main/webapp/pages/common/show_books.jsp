
<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays all books
  displays forms to looking for a book
  it is possible to update/delete a book (for admin)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.show_books" bundle="${rb}" /></title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="header.jsp" %>
<br>

<%--the form for a looking a book by the title--%>
<div id="show-books-search">
    <form action="/library/controller?param=search_book" method="post">
        <input name="action" type="hidden" value="search"/>
        <input id="show-books-search-field" type="text" name="search_book"
               value="<fmt:message key="search.book" bundle="${rb}"/>" />
        <input type="submit" id="show-books-search-button"
               value="<fmt:message key="search" bundle="${rb}"/>">
    </form>
</div>

<%--the form to select books by a category--%>
<div id="show-books-search">
    <div id="show-books-text">
        <fmt:message key="search.byCategory" bundle="${rb}"/></div>
    <form action="/library/controller?param=select_by_category" method="post">
            <select name="category">
            <option value="1">
                <fmt:message key="book.category.education" bundle="${rb}"/></option>
            <option value="2">
                <fmt:message key="book.category.historical" bundle="${rb}"/></option>
            <option value="3">
                <fmt:message key="book.category.science_fiction" bundle="${rb}"/></option>
            <option value="4">
                <fmt:message key="book.category.classical" bundle="${rb}"/></option>
            <option value="5">
                <fmt:message key="book.category.philosophy" bundle="${rb}"/></option>
            </select>
        <input type="submit" name="apply" value="<fmt:message key="choose" bundle="${rb}"/>"
               id="show-books-search-category-button">
    </form>
</div>

<%--displays a message if there is/are book/books in the library--%>
<div class="show_books_message">${no_book}</div>
<div class="show_books_message">${no_books_in_category}</div>

<%--displays a message if it's impossible to delete a book--%>
<div class="show_books_message">${book_delete}</div>

<%--displays a message if it's impossible to edit a book--%>
<div class="show_books_message">${book_edit}</div>
<br>

<%--displays a message if a book had already ordered by the reader--%>
<div class="show_books_message">${book_already_ordered}</div>
<br>



<%--displays a table with books--%>
<div id="show-books-table-div">
    <table id="show-books-table">
        <%--displays titles for fields of the table--%>
        <th id="show-books-th"><fmt:message key="book.title" bundle="${rb}"/></th>
        <th id="show-books-th"><fmt:message key="book.availability" bundle="${rb}"/></th>
        <c:choose>
            <%--displays these links for admin (role 1)--%>
        <c:when test="${sessionScope.reader.role == 1}">
            <th id="show-books-th"><fmt:message key="title.manage_book" bundle="${rb}"/></th>
        </c:when>
        <c:otherwise>
            <th id="show-books-th"><fmt:message key="title.lend_out" bundle="${rb}"/></th>
        </c:otherwise>
        </c:choose>


        <%--displays the information about a book--%>
        <c:forEach var="book" items="${requestScope.books}"><tr id="tr1">
            <%--the title--%>
            <td id="show-books-td">
                <a href="/library/controller?param=take_info_about_book&id=${book.id}"
                            id="show-books-href-view-book">
                            ${book.title}</a>
            </td>
            <%--count--%>
            <td id="show-books-td">
                <c:choose>
                    <c:when test="${book.count > 0}">
                    ${book.count}
                    </c:when>
                    <%--displays information if a book is unavailable--%>
                    <c:otherwise>
                        <div id="show-books-unavailable">
                            <fmt:message key="book.unavailable" bundle="${rb}"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </td>
            <%--if the user is a registrated reader displays lending library
            and reading hall links--%>
            <td id="show-books-td"><c:if test="${sessionScope.reader.role == 2}">
                <c:choose>
                    <%--if the quantity of a book 1 and more displays these links--%>
                    <c:when test="${book.count > 0}">
                        <a href="controller?param=order_book&id=${book.id}&type=lending_library"
                           id="show-books-href-lending">
                            <%--the link to order a book to lending library--%>
                            <img src="image/icon/lending_library_icon.png"
                                 title="<fmt:message key="lending_library" bundle="${rb}"/>"></a>
                        &nbsp &nbsp &nbsp &nbsp &nbsp
                        <%--the link to order a book to the reading hall--%>
                        <a href="controller?param=order_book&id=${book.id}&type=reading_hall"
                           id="show-books-href-reading-hall">
                            <img src="image/icon/reading_hall_icon.jpg"
                                 title="<fmt:message key="reading_hall" bundle="${rb}"/>"></a>
                    </c:when>
                </c:choose>
            </c:if>
            <%--displays these links for admin (role 1)--%>
                <c:if test="${sessionScope.reader.role == 1 and book.count > 0 }">
                <%--the link to update a book--%>
                    <a href="controller?param=take_book_by_id&id=${book.id}"
                       id="show-books-href-update">
                        <img src="image/icon/update_book_icon.png"
                             title="<fmt:message key="book.edit" bundle="${rb}"/>">
                    </a>
                    <%--the link to delete a book--%>
                    &nbsp &nbsp &nbsp &nbsp &nbsp
                    <a href="controller?param=delete_book&id=${book.id}"
                       id="show-books-href-delete">
                        <img src="image/icon/delete_book_icon.png"
                             title="<fmt:message key="book.delete" bundle="${rb}"/>">
                    </a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
<br>
<br>

<%--the link to see all books --%>
<div id="show-books-see-all-book">
    <a href="controller?param=take_books" id="href-see-all-books">
        <fmt:message key="href.book.see_all_books" bundle="${rb}"/></a>
</div>
<br>

<%--the link to see orders of a reader--%>
<div id="show-books-see-all-book">
    <a href="controller?param=path_order_page" id="order_href-see-orders">
        <fmt:message key="orders" bundle="${rb}"/></a>
</div>

<%--the footer--%>
<%@ include file="footer.jsp" %>

</body>
</html>