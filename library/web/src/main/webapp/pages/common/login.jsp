
<%--
  Created by Polina Tolkachyova
  Date: 01.08.2015

  displays a form to login in the library
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/pages/admin/include_language.jsp" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="style/bootstrap.min.css">

    <title><fmt:message key="page.login" bundle="${rb}"/></title>
</head>

<body id="login-body">
<br>

<%--displays pictures to change a language--%>
<div id="login-pictures">
    <a href="/library/controller?param=change_locale&lang=by">
        <img class="flag" src="image/flag/flag_by.png" alt="BY"></a>
    <a href="/library/controller?param=change_locale&lang=en">&nbsp
        <img class="flag" src="image/flag/flag_en.png" alt="EN"></a>
    <a href="/library/controller?param=change_locale&lang=kr">&nbsp
        <img class="flag" src="image/flag/flag_kr.png" alt="KR"></a>
</div>

<%--the header--%>
<%@ include file="header.jsp" %>

<%--displays the current time and date--%>
<div id="login-current-time">
    <ctg:info_time/>
</div>
<br>
<br>

<%--displays a name of the library--%>
<div id="login-library-name">
    <fmt:message key="text.greeting" bundle="${rb}"/>
</div>
<br>
<br>
<br>

<%--displays the login form--%>
<div id="login-form-table-div">
    <table id="login-form-table">
        <tr>
            <td>
                <br>
                <%--displays a message if a password isn't correct--%>
                <div id="login-message">
                    ${incorrect_password_message}
                </div>
               <%--displays a message if an account isn't exist--%>
                <div id="login-message">
                ${not_exist_account_message}
                </div>
                <br>
                <form method="post" action="/library/controller?param=verify_login"
                      class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label">
                            <fmt:message key="title.mail" bundle="${rb}"/></label>
                        <div class="controls">
                             <input type="text" name="mail" id="login-form-mail"
                                    required title="<fmt:message key="validate.field" bundle="${rb}" />">
                         </div>
                     </div>
                     <div class="control-group">
                         <label class="control-label">
                             <fmt:message key="title.password" bundle="${rb}"/></label>
                         <div class="controls">
                             <input type="password" name="password" id="login-form-password"
                                    required title="<fmt:message key="validate.field" bundle="${rb}" />">
                         </div>
                     </div>
                     <div class="control-group">
                         <div class="controls">
                             <button type="submit" id="login-form-button">
                                 <fmt:message key="title.enter" bundle="${rb}"/></button>
                         </div>
                     </div>
                </form>
            </td>
        </tr>

        <%--displays a registration link--%>
        <div id="login-registration-link-div">
            <a href="/library/controller?param=path_registration_page"
               id="login-registration-link">
                <fmt:message key="title.registration" bundle="${rb}"/></a>
        </div>
        <br>
    </table>
</div>
</body>
</html>