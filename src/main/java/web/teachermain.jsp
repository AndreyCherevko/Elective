<%-- 
    Document   : teachermain
    Created on : 01.06.2014, 17:28:00
    Author     : ??????
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
<html>
    <head>
        <c:choose>
            <c:when test="${local!=null}">
                <fmt:setLocale value="${local}"/>
            </c:when>
        </c:choose>
        <fmt:setBundle basename="servlet.properties.locale"/>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title><fmt:message key="TEACHER_MAIN_TITLE"/></title>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <body>
        <br><h1 align="center"><font size="7" color="#55acee"><fmt:message key="TEACHER_MAIN_TITLE"/></h1>
        <hr/>
        <form name="index" method="POST" action="controller">
            <input type="hidden" name="command" value="redirect"/>
            <c:set var="list" value="${list}" scope="session"/>
            <c:set var="id" value="${id}" scope="session"/>
            <table >
                <tr><td><button class="btn blue" type="submit" name="goto"  value="TEACHER_STUDENT_LIST" ><fmt:message key="STUDENT_IN_ARCHIVE"/></button></td>
                    <td><button class="btn blue" type="submit" name="goto"  value="TEAHCER_STUDENT_REQUEST" ><fmt:message key="CONFIRMSTUDENTREQUEST"/></button></td>
                    <td><button class="btn blue" type="submit" name="goto"  value="LOGIN_PAGE_PATH" onclick="<%request.logout();%>"><fmt:message key="LOGOUT"/></button></td></tr>
            </table>
        </form><hr/>
    </body>
</html>