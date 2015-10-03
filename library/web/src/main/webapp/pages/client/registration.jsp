<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays the registration form
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/pages/admin/include_language.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.registration" bundle="${rb}"/></title>
</head>

<body class="body_pages">

<%--the header--%>
<%@ include file="../common/header.jsp" %>

<%--displays the title of the registration form--%>
<div id="registration-title-form">
    <fmt:message key="title.registration_form" bundle="${rb}"/></div>
<br>

<%--displays a message if the reader is already exist in the data base--%>
<div id="registration-message">
    ${registration_message}
</div>
<br>

<%--displays the registration form--%>
<div id="registration-form-div">
    <table id="registration-table">
        <tr>
            <td>
                <form method="post" action=/library/controller?param=registration
                      class="form-horizontal">
                    <br>
                    <br>
                    <div class="control-group">
                        <label class="control-label">
                            <fmt:message key="reader.name.registration" bundle="${rb}"/>
                        </label>
                        <%--name--%>
                        <div class="controls">
                            <input type="text" name="name"
                                   pattern="(^[A-Z\s][a-z\s]{0,14}$)\b" required>
                        </div>
                    </div>
                    <div  class="control-group">
                        <label class="control-label">
                            <fmt:message key="reader.surname.registration" bundle="${rb}"/>
                        </label>
                        <%--surname--%>
                        <div class="controls">
                            <input type="text" name="surname"
                                   pattern="(^[A-Z\s][a-z\s]{0,14}$)\b" required>
                        </div>
                    </div>
                    <div  class="control-group">
                        <label class="control-label">
                            <fmt:message key="reader.mail.registration" bundle="${rb}"/>
                        </label>
                        <%--mail--%>
                        <div class="controls">
                            <input type="text" name="mail"
                                   pattern="^[-\w.]{1,15}@([A-z0-9][-A-z0-9]{0,14}\.)+[A-z]{2,4}$" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            <fmt:message key="reader.password.registration" bundle="${rb}"/>
                        </label>
                        <%--password--%>
                        <div class="controls">
                            <input type="password" name="password" id="inputPassword"
                                   pattern="(^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{0,15}$)" required>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" id="registration-button">
                                <fmt:message key="title.register" bundle="${rb}"/></button>
                        </div>
                    </div>
                </form>
            </td>
        </tr>
    </table>
</div>
<br>

<%--displays a message if inputted data isn't correct--%>
<div class="registration_message">${invalid_name}</div>
<div class="registration_message">${invalid_surname}</div>
<div class="registration_message">${invalid_mail}</div>
<div class="registration_message">${invalid_password}</div>
<br>

<%--information about the rules to input correct data to the form--%>
<div id="registration-validate-info">
<fmt:message key="validate.name" bundle="${rb}" />
<br>
<br>
<fmt:message key="validate.password" bundle="${rb}" />
</div>
<br>
<br>

<%--displays the link to pass the login page--%>
<div><a href="/library/" id="href-logout">
    <fmt:message key="href.login_page" bundle="${rb}"/></a></div>
<br>
<br>

<%--the footer--%>
<%@ include file="../common/footer.jsp" %>

</body>
</html>