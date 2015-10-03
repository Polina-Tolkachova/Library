
<%--
Created by Polina Tolkachyova
Date: 01.08.2015

thanks an user for the registration
--%>
<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
        include file="/pages/admin/include_language.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title>
        <fmt:message key="page.confirm_registration"
                     bundle="${rb}" />
    </title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="../common/header.jsp" %>

<%--displays the message after registration of an user--%>
<div id="confirm-registration-message">
    <fmt:message key="message.thanks_for_registration" bundle="${rb}" />
</div>
<br>
<br>
<br>

<%--the link to enter to the library by id--%>
<a href="/library/controller?param=path_login_page" id="confirm-registration-login-href">
    <fmt:message key="href.enter_by_password" bundle="${rb}" />
</a>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<%--the footer--%>
<%@ include file="../common/footer.jsp" %>

</body>
</html>
