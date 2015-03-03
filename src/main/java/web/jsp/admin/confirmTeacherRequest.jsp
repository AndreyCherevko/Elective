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
        <title><fmt:message key="TEACHER_REQUEST"/></title>
    </head>
     <link rel="stylesheet" href="css/style.css">
    <body>
        <clist:GetAllNotConfirmUser/>
        <c:choose>
            <c:when test="${list.size()>0}">
                <table border="1">
                    <form name="courseform" method="POST" action="controller">
                        <tr>
                            <th><fmt:message key="NAME"/></th>
                            <th><fmt:message key="SURNAME"/></th>
                            <th><fmt:message key="MIDDLENAME"/></th>
                        </tr>
                        <tr>
                            <c:forEach var="user" items="${list}">
                                <td><input type="checkbox" name="userid" value="${user.id}"><c:out value="${user.name} "/></td>
                                <td><c:out value="${user.surname}"/></td>
                                <td><c:out value="${user.middlename}"/></td>
                            </tr>
                        </c:forEach>
                </table> 
            </c:when>
            <c:otherwise>
                <br><h1 align="center"><font size="7" color="#55acee"><fmt:message key="NOT_HAVE_TEACHER_REQUEST"/> </font></h1></c:otherwise></c:choose>
                <form name="courseform" method="POST" action="controller">
                    <input type="hidden" name="goto" value="ADMIN_MAIN"/>
                     <center><button class="btn blue" type="submit" name="command" value="confirmTeacherRequest"><fmt:message key="ENTER"/></button>
                    <button class="btn blue" type="submit" name="command" value="redirect"><fmt:message key="ADMIN_MAIN_TITLE"/></button></center>
                    
    </body>
</html>
