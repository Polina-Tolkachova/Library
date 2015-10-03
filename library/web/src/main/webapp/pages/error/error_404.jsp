<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays if gets 404 error (Not Found)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.error" bundle="${rb}" /></title>
</head>

<body id="error-page-body">
<br>
<br>
<br>
<br>
<br>

<%--displays the name of error--%>
<div id="concrete-error-message"><fmt:message key="error.404" bundle="${rb}" /></div>


</body>
</html>