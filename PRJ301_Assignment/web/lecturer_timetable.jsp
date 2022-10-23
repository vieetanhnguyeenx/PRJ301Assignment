<%-- 
    Document   : lecturer_timtable
    Created on : Oct 20, 2022, 4:17:35 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="helper.DateTimeHelper"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="lecturer-timetable" method="GET">
            <input style="display: none" type="text" name="lid"  value="${requestScope.lecturer.id}"/>
            <table>
                <tbody>
                    <tr>
                        <td>Lecture Name</td>
                        <td>
                            
                            <input type="text" value="${requestScope.lecturer.name}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            From: <input type="date" name="from" value="${requestScope.from}"/>
                        </td>
                        <td>
                            To: <input type="date" name="to" value="${requestScope.to}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="View"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <table border="1">
                <tbody>
                    <tr>
                        <td></td>
                        <c:forEach items="${requestScope.dates}" var="d" >
                            <td>${d.toString()}<br/>${helper.getDayNameOfWeek(d)}</td>
                            </c:forEach>
                    </tr>
                    <c:forEach items="${requestScope.slots}" var="s">
                        <tr>
                            <td>${s.description}</td>
                            <c:forEach items="${requestScope.dates}" var="d">
                                <td>
                                    <c:forEach items="${requestScope.sessions}" var="ss">
                                        <c:if test="${helper.isEqual(d, ss.date) == 1 and ss.timeslot.id eq s.id}">
                                            <a href="attendent?id=${ss.id}">${ss.group.name}-${ss.group.subject.name}</a>
                                            <br/>
                                            at ${ss.room.name}<br/>
                                            <c:if test="${ss.attandated}">
                                                (Attandated)
                                            </c:if>
                                            <c:if test="${!ss.attandated}">
                                                (Not yet)
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </form>





    </body>
</html>
