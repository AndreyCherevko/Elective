
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
        <clist:GetAllTeacherList/>
        <c:choose>
            <c:when test="${teachers.size()>0}">
                    <table style="position: absolute; top:0px; left:0px;overflow:hidden;" name="box1" border="1" >
                    <form name="newcourseform" method="POST" action="controller">

                        <tr><th><fmt:message key="NAME"/></th>
                            <th><fmt:message key="SURNAME"/></th>
                            <th><fmt:message key="MIDDLENAME"/></th>
                        </tr>
                        <tr>
                            <c:forEach var="teacher" items="${teachers}">
                                <td>
                                    <input type="checkbox" name="user" value="${teacher.id}"><c:out value="${teacher.name}"/></td>
                                <td><c:out value="${teacher.surname}"/></td>
                                <td><c:out value="${teacher.middlename}"/></td>
                            </tr> </c:forEach> </table>
               
            </c:when>
            <c:otherwise><br> <h1 align="center"><font size="7" color="#55acee"><fmt:message key="NOT_TEACHERS"/></font></h1></c:otherwise></c:choose>
               
        <clist:GetAllClist/>
        <c:if test="${allclist.size()>0}">
            <table border="1" style="position:absolute; top:0px; left:25%;  overflow:auto;">
                <tr><th><fmt:message key="COURSES_LIST"/></th></tr>
                        <c:forEach var="clist" items="${allclist}">
                    <tr><td>${clist.name}</td></tr>
                </c:forEach></table></c:if>
                 <ul style="position:absolute; top:60%; left:0%;  overflow:auto;"><li><input type="hidden" name="goto" value="ADMIN_MAIN"/>
                         <font size="4" color="#55acee"><fmt:message key="COURSE_NAME"/></font></li>
                     <li><input type="text" name="name"/></li>
                     <li><button class="btn blue" type="submit" name="command" value="setCoursesList"><fmt:message key="ENTER"/></button>
                         <button class="btn blue" type="submit" name="command" value="redirect"><fmt:message key="ADMIN_MAIN_TITLE"/></button></li></ul>
    </body>
</html>
