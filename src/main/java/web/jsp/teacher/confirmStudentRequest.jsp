
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
        <clist:GetAllNoConfirmCourses/>
        <c:choose>
            <c:when test="${notConfirmList.size()>0}">
                <table border="1" cellspacing="0">
                    <form name="courseform" method="POST" action="controller">
                        
                        <tr>
                        <th><fmt:message key="COURSE"/></th>
                            <th><fmt:message key="NAME"/></th>
                            <th><fmt:message key="SURNAME"/></th>
                            <th><fmt:message key="MIDDLENAME"/></th>
                        </tr>
                        <tr>
                            <c:forEach var="course" items="${notConfirmList}">
                                <clist:GetCoursesAndUser/>
                                <td><input type="checkbox" name="courseid" value="${course.id}"><c:out value="${course.name} "/></td>
                                <td><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.surname}"/></td>
                                <td><c:out value="${user.middlename}"/></td>
                            </tr>
                        </c:forEach>
                </table> 
            </c:when>
            <c:otherwise><br>
                <h1 align="center"><font size="7" color="#55acee"><fmt:message key="NOT_HAVE_STUDENT_REQUEST"/></font></h1> </c:otherwise></c:choose>
                <form name="courseform" method="POST" action="controller">
                    <input type="hidden" name="goto" value="TEACHER_MAIN_PAGE_PATH"/>
                    <center><button class="btn blue" type="submit" name="command" value="confirmStudentRequest"><fmt:message key="ENTER"/></button>
                    <button class="btn blue" type="submit" name="command" value="redirect"><fmt:message key="TEACHER_MAIN_TITLE"/></button></center>
               
    </body>
</html>
