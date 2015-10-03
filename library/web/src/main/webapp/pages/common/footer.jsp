<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays information at the bottom of pages (footer)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.footer" bundle="${rb}" /></title>
</head>
<body>

<br>
<br>
<br>
<%--the citation--%>
<div id="footer-text">
    <fmt:message key="footer.text" bundle="${rb}" />
</div>

<%--copyright notice--%>
<div id="footer-tag">
    <ctg:footer_tag />
</div>

</body>
</html>