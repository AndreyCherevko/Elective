

<%@page import="java.util.Collection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/tlds/coursesteg.tld" prefix="clist" %>
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
        <title><fmt:message key="ARCHIVE_LIST"/></title>
    </head>
    <link rel="stylesheet" href="css/style.css">
    <body>
        <clist:GetArchiveForUser/>
        <c:choose>
            <c:when test="${list.size()>0}">
                <table border="1">
                    <form name="SeTForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="redirect"/>
                        <input type="hidden" name="goto" value="STUDENT_MAIN_PAGE_PATH"/>
                        <tr><th><fmt:message key="COURSE_NAME"/></td></th>
                            <th><fmt:message key="MARK"/></th>
                        </tr>
                        <c:forEach var="course"  items="${list}">
                            <c:set var="currentcourse" value="${course}"/> 
                            <clist:getMark/>
                            <tr>
                                <td><c:out value="${name}"/></td>
                                <td><c:out value="${mark}"/></td>
                            </tr>
                        </c:forEach>
                </table> </c:when>
            <c:otherwise> 
                <br>
                <h1 align="center"><font size="7" color="#55acee"><fmt:message key="EMPTY_ARCHIVE"/></p></font></h1>
                </c:otherwise></c:choose>
            <form name="SeTForm" method="POST" action="controller">
                <input type="hidden" name="command" value="redirect"/>
                <input type="hidden" name="goto" value="STUDENT_MAIN_PAGE_PATH"/>
                <center><button class="btn blue"type="submit" ><fmt:message key="STUDENT_MAIN_TITLE"/></button></center>


            </body>
            </html>
