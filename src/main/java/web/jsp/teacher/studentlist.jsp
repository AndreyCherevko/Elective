
<%@page import="classes.entity.Users"%>
<%@page import="classes.entity.Courses"%>
<%@page import="classes.entity.Courses"%>
<%@page import="classes.idao.IUsersDao"%>
<%@page import="classes.idao.ICoursesDao"%>
<%@page import="classes.daoFactory.DaoFactory.Databases"%>
<%@page import="classes.daoFactory.DaoFactory"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/tlds/coursesteg.tld" prefix="stlist" %>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <c:choose>
        <c:when test="${local!=null}">
            <fmt:setLocale value="${local}"/>
        </c:when>
       </c:choose>
        <fmt:setBundle basename="servlet.properties.locale"/>
        <meta charset="utf-8">
        <title><fmt:message key="STUDENTS_LIST"/></title>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <body>
        <stlist:GetAllCourses/>
        <c:choose>
            <c:when test="${courses.size()>0}">
                <table border="1">

                    <form name="SeTForm" method="POST" action="controller">
                        
                            
                        <tr> <th><fmt:message key="NAME"/></th>
                            <th><fmt:message key="SURNAME"/></th>
                            <th><fmt:message key="MIDDLENAME"/></th>
                            <th><fmt:message key="PHONE"/></th>
                            <th><fmt:message key="COURSE"/></th>
                            <th><fmt:message key="MARK"/></th>

                            <c:forEach var="courses" items="${courses}">
                                <stlist:GetCoursesAndUser/>
                            <tr><td>
                                    <input type="checkbox" name="courseid" value="${course.id}"><c:out value="${user.name}"/></td>
                                <td><c:out value="${user.surname}"/></td>
                                <td><c:out value="${user.middlename}"/></td>
                                <td><c:out value="${user.phone}"/><c:out value=""/></td>
                                <td> 
                                    <c:out value="${course.name}"/> 
                                </td>
                                <td><input type="radio" name="mark" value="3" checked>3</input>
                                    <input type="radio" name="mark" value="4">4</input>
                                    <input type="radio" name="mark" value="5">5</input></td>
                            </tr>

                        </c:forEach>

                </table> 
            
            </c:when>
            <c:otherwise> <br><h1 align="center"><font size="7" color="#55acee"><fmt:message key="NO_STUDENTS"/> </font></h1></c:otherwise></c:choose>
                <form name="SeTForm" method="POST" action="controller">
                    <input type="hidden" name="goto" value="TEACHER_MAIN_PAGE_PATH"/>
                    <center><button class="btn blue" type="submit"  name="command" value="addArchive"><fmt:message key="ENTER"/></button>
                    <button class="btn blue" type="submit"  name="command" value="redirect"><fmt:message key="TEACHER_MAIN_TITLE"/></button></center>
               
                  

    </body>
</html>
