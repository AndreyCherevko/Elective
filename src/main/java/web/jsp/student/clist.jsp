
<%@ taglib uri="/tlds/coursesteg.tld" prefix="clist" %>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:choose>
            <c:when test="${local!=null}">
                <fmt:setLocale value="${local}"/>
            </c:when>
        </c:choose>
        <fmt:setBundle basename="servlet.properties.locale"/>
        <meta charset="utf-8">
        <title><fmt:message key="COURSES_LIST"/></title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <clist:GetAvailableCourses/>
        <c:choose>
            <c:when test="${list.size()>0}">
                <table     cellspacing="0"  border="1">
                    <form name="SeTForm" method="POST" action="controller">
                        <input type="hidden" name="id" value="${id}"/>
                        <tr><th><fmt:message key="COURSE_NAME"/></th>
                        </tr>
                        <tr class='even'>
                            <c:forEach var="course" items="${list}">
                                <td >
                                <input type="checkbox" name="name" value="${course.name}"><c:out value="${course.name}"/></tr>
                            </c:forEach>
                </table> 
                            </c:when>
            <c:otherwise> <br><h1 align="center"><font size="7" color="#55acee"><fmt:message key="NOT_COURSE"/></font></h1></c:otherwise></c:choose>
                <form name="SeTForm" method="POST" action="controller">
                    <input type="hidden" name="goto" value="STUDENT_MAIN_PAGE_PATH"/>
                    <center><button class="btn blue" type="submit" name="command" value="set"><fmt:message key="ENTER"/></button>
                    <button class="btn blue"  type="submit" name="command" value="redirect"><fmt:message key="STUDENT_MAIN_TITLE"/></button></center>

    </body>
</html>
