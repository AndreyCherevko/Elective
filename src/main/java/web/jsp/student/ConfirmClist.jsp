
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
    </head>
     <link rel="stylesheet" href="css/style.css">
    <body>
        <clist:GetConfirmStudentCourses confirm="true"/>
        <c:choose>
            <c:when test="${requestlist.size()>0}">
                <table border="1"  cellspacing="0" >
                    <form name="courseform" method="POST" action="controller">
                        <input type="hidden" name="command" value="redirect"/>
                        <tr>
                            <th><fmt:message key="COURSE_NAME"/></th>
                        </tr>
                        <c:forEach var="course" items="${requestlist}">
                            <tr class='even'>
                                <td>
                                <c:out value="${course.name}"/></tr>
                            </c:forEach>
                </table> 
            <center><button class="btn blue" type="submit" name="goto" value="STUDENT_MAIN_PAGE_PATH"><fmt:message key="STUDENT_MAIN_TITLE"/></button></center>
            </c:when>
            <c:otherwise>
            <br>
            <h1 align="center"><font size="7" color="#55acee"><fmt:message key="NOT_CONFIRM_COURSE"/></font></h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>