
<%--
Created by Polina Tolkachyova.
Date: 01.08.2015

sets a local language
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
</head>
<body>

<c:if test="${sessionScope.locale == 'by' or empty sessionScope.locale}">
    <fmt:setLocale value="by" scope="session" />
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
    <fmt:setLocale value="en" scope="session" />
</c:if>
<c:if test="${sessionScope.locale == 'kr'}">
    <fmt:setLocale value="kr" scope="session" />
</c:if>
<fmt:setBundle basename="pagecontent.pagecontent" var="rb" />

</body>
</html>