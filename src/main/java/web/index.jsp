<%-- 
    Document   : login
    Created on : 1 ???? 2010, 11:38:30
    Author     : Family
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <c:choose>
        <c:when test="${local!=null}">
            <fmt:setLocale value="${local}"/>
            <c:set var="local" value="${local}" scope="session"/>
        </c:when>
    </c:choose>
    <fmt:setBundle basename="servlet.properties.locale"/>
<form name="Bundle" method="POST" action="controller"  >
    <select name="loc">
        <option  value="ru" ><fmt:message key="RUSSIAN"/></option>
        <option value="en"><fmt:message key="ENGLISH"/></option>
    </select>
    <button  type="submit" name="command" value="changeLan"><fmt:message key="CHANGELANG"/></button>
</form>
<title><fmt:message key="TITLE_LOGIN"/></title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>
    
    <form  name="loginForm" method="POST" action="controller" class="login">
        <input type="hidden" name="goto" value="REG_PAGE_PATH"/>
        <p >
            <label for="login"><fmt:message key="LOGIN"/>:</label>
            <input type="text" id="login" name="login" />
        </p>

        <p >

            <label for="password"> <fmt:message key="PASSWORD"/>:</label>
            <input type="password" id="password" name="password" />
        </p>
        <p class="login-submit">
            <button  type="submit" name="command" value="login" class="login-button"/>
    </p>
       <p ><button style="position: absolute; top:115%; right:80px;overflow:hidden;" class="btn blue" type="submit"  name="command" value="redirect" ><fmt:message key="REG"/></button>
   </p>
    </form>
           
           
            
</body>
</html>