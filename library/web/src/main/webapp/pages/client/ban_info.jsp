<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  shows to a reader if he/she is uder a ban
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ include file="/pages/admin/include_language.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">
    <title><fmt:message key="page.ban" bundle="${rb}" /></title>
</head>

<body class="body_pages">
<br>
<br>

<%--the link to the main page--%>
<div>
    <a href="/library/" id="href-logout"> <fmt:message
            key="href.login_page" bundle="${rb}" /></a>
</div>
<br>
<br>
<br>
<br>
<br>

<%--the message for a reader--%>
<div id="ban-message">
    <fmt:message key="message.ban" bundle="${rb}" />
</div>

</body>
</html>