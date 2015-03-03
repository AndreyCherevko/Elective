<%-- 
    Document   : login
    Created on : 1 ???? 2010, 11:38:30
    Author     : Family
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${local!=null}">
        <fmt:setLocale value="${local}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="servlet.properties.locale"/>



<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><fmt:message key="REG"/></title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>

    <!-- vladmaxi top bar -->

    <div class="cls"></div>
    <!--/ vladmaxi top bar -->

    <form method="post" action="controller" class="login">
        <p>
            <label for="login"><fmt:message key="LOGIN"/>:</label>
            <input type="text" name="login" >
        </p>

        <p>
            <label for="password"><fmt:message key="PASSWORD"/>:</label>
            <input type="password" name="password" >
        </p>
        <p>
            <label for="name"><fmt:message key="NAME"/>:</label>
            <input type="text" name="name">
        </p>
        <p><label for="surname"><fmt:message key="SURNAME"/>:</label>
            <input type="text" name="surname" >
        </p>
        <p><label for="middlename"><fmt:message key="MIDDLENAME"/>:</label>
            <input type="text" name="middlename" >
        </p>
        <p><label for="phone"><fmt:message key="PHONE"/>:</label>
            <input type="text" name="phone" >
        </p>
        <p><label for="usertype"><fmt:message key="USER_TYPE"/>:</label>
            <input type="text" name="usertype">
        </p>
        <p class="login-submit">
            <button type="submit" name="command" value="reg" class="login-button"/>
        </p>


    </form>
</body>
</html>
