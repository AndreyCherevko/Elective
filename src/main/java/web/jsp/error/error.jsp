<%-- 
    Document   : error
    Created on : 01.06.2014, 15:50:09
    Author     : Андрей
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <fmt:setBundle basename="servlet.properties.locale"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="ERROR"/></title>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <body><br>
         <h1 align="center"><font size="7" color="#55acee"><c:out value="${error.toString()}"/></h1>
    </body>
</html>
