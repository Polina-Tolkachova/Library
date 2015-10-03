<%--
Created by Polina Tolkachyova.
Date: 01.08.2015

displays the form to add or updates a book
--%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include
        file="/pages/admin/include_language.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=cp1251">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">
    <title>
        <fmt:message key="page.edit_book" bundle="${rb}" />
    </title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="../common/header.jsp"%>
<br>
<br>

<%--displays a title of update form--%>
<div id="update_title">
    <fmt:message key="title.update_book" bundle="${rb}"/>
</div>

<div id="update-add-book-main-div">

    <%--the form to add or update a book--%>
    <table id="update-add-book-table">
        <form action="/library/controller?param=add_update_book"
              method="post">
            <input type="hidden" name="id" value="${requestScope.book.id}">
            <%--gets an attribute from TakeBookByIdCommand--%>
            <br>
            <br>
            <tr id="tr">
                <%--book title--%>
                <td id="update-add-book-td-title">
                    <fmt:message key="book.title.add_book" bundle="${rb}" />
                </td>
                <td>
                    <br>
                    <br>
                    <br>
                    <input type="text" name="name" class="update_add_book_text"
                           value="${requestScope.book.title}"
                           pattern="(^[А-ЯЁІЎ]+[А-ЯЁІЎа-яёіў\s\,\-\?\.\:\(\)\d]{0,29}$)|(^[A-Z]+[\w\s\,\-\?\.\:\(\)\d]{0,29}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]+[ㄱ-ㅎㅏ-ㅣ가-힣\s\,\-\?\.\:\(\)\d]{0,19}$)"
                           required title="<fmt:message key="validate.date" bundle="${rb}" />">
                </td>
            </tr>
            <tr>
                <%--author's name--%>
                <td class="update_add_book_td">
                    <fmt:message key="author.name.add_book" bundle="${rb}" />
                </td>
                <td>
                    <input type="text" name="author_name" class="update_add_book_text"
                           value="${requestScope.author_name}"
                           pattern="(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}\s{1}[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}\s{1}[A-Z]{1}[a-z]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}\s{1}[ㄱ-ㅎㅏ-ㅣ가-힣]{0,15}$)"
                           required title="<fmt:message key="validate.date" bundle="${rb}" />">
                </td>
            </tr>
            <tr>
                <%--author's surname--%>
                <td class="update_add_book_td">
                    <fmt:message key="author.surname.add_book" bundle="${rb}" />
                </td>
                <td>
                    <input type="text" name="author_surname" class="update_add_book_text"
                           value="${requestScope.author_surname}"
                    <%--gets an attribute from TakeBookByIdCommand--%>
                           pattern="(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[А-ЯЁІЎ]{1}[а-яёіў]{0,14}\s{1}[А-ЯЁІЎ]{1}[а-яёіў]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}$)|(^[A-Z]{1}[a-z]{0,14}\s{1}[A-Z]{1}[a-z]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}$)|(^[ㄱ-ㅎㅏ-ㅣ가-힣]{0,14}\s{1}[ㄱ-ㅎㅏ-ㅣ가-힣]{0,15}$)"
                           required title="<fmt:message key="validate.date" bundle="${rb}" />">
                </td>
            </tr>
            <tr>
                <%--the categories --%>
                <td class="update_add_book_td">
                    <fmt:message key="book.category.add_book" bundle="${rb}" />
                </td>
                <td>
                    <select name="category" class="update_add_book_text">
                        <option value="1">
                            <fmt:message key="book.category.education" bundle="${rb}" />
                        </option>
                        <option value="2">
                            <fmt:message key="book.category.historical" bundle="${rb}" />
                        </option>
                        <option value="3">
                            <fmt:message key="book.category.science_fiction" bundle="${rb}" />
                        </option>
                        <option value="4">
                            <fmt:message key="book.category.classical" bundle="${rb}" />
                        </option>
                        <option value="5">
                            <fmt:message key="book.category.philosophy" bundle="${rb}" />
                        </option>
                    </select>
                </td>
            </tr>
            <tr>
                <%--quantity of books --%>
                <td class="update_add_book_td">
                    <fmt:message key="message.book_store" bundle="${rb}" />
                </td>
                <td>
                    <input type="text" name="count" class="update_add_book_text"
                           value="${requestScope.book.count}" pattern="[1-9]{0,2}|20?|30?|40?|50?|60?|70?|80?|90?|100?"
                           required title="<fmt:message key="validate.date" bundle="${rb}" />">
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <%--the submit bottom--%>
                <td id="update-add-book-td-submit">
                    <input type="submit" name="apply" id="update-add-book-form-submit"
                           value="<fmt:message key="book.apply" bundle="${rb}" />">
                </td>
            </tr>
        </form>
    </table>
    <br>
    <br>

    <%--displays a message if input information had not been
    validated--%>
    <div class="update_add_book_message">
        ${invalid_title}
        ${invalid_name}
        ${invalid_surname}
        ${invalid_quantity}
    </div>
    <br>

    <%--information about the rules to input correct data to the form--%>
    <div id="update-add-book-validate-info">
        <fmt:message key="validate.book_title" bundle="${rb}" />
        <br>
        <br>
        <fmt:message key="validate.author_name" bundle="${rb}" />
        <br>
        <br>
        <fmt:message key="validate.book_quantity" bundle="${rb}" />
    </div>
    <br>
    <br>

    <%--the link to see all books --%>
    <div>
        <a href="controller?param=take_books" id="href-see-all-books">
            <fmt:message key="href.book.see_all_books" bundle="${rb}" />
        </a>
    </div>
    <br>
    <br>

    <%--the footer--%>
    <%@ include file="../common/footer.jsp"%>

</body>
</html>
