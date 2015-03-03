<%-- 
    Document   : indexforstudent
    Created on : 01.06.2014, 11:23:36
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
        <c:set var="id" value="${id}" scope="session"/>
        <title><fmt:message key="STUDENT_MAIN_TITLE"/></title>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <body>
        <br><h1 align="center"><font size="7" color="#55acee"><fmt:message key="STUDENT_MAIN_TITLE"/></h1><br>
        <form name="index" method="POST" action="controller">
            <input type="hidden" name="command" value="redirect"/>
            <table border="0" width="100%" cellpadding="3" background="" >
                <tr>
                    <td><button  class="btn blue" type="submit" name="goto"  value="STUDENT_CLIST"><fmt:message key="SET_COURSE"/></button></td>
                    <td><button  class="btn blue" type="submit" name="goto"  value="STUDENT_NOT_CONFIRM_CLIST" ><fmt:message key="NOTCONFRIMCLIST"/></button></td>
                    <td><button class="btn blue" type="submit" name="goto"  value="STUDENT_CONFIRM_CLIST" ><fmt:message key="CONFRIMCLIST"/></button></td>
                    <td><button class="btn blue"  type="submit" name="goto"  value="STUDENT_CURRENT_ARCHIVE" ><fmt:message key="FINISHED_COURSE_LIST"/></button></td>
                    <td><button class="btn blue" type="submit" name="goto"  value="LOGIN_PAGE_PATH" onclick="<%request.logout();%>"><fmt:message key="LOGOUT"/></button></td>
                </tr>
            </table>
        </form>

    </body>
</html>
