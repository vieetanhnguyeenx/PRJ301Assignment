<%-- 
    Document   : schedule_of_week
    Created on : Oct 23, 2022, 11:34:01 AM
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
        <form action="scheduleofweek" method="GET">
            <h1> Activities for ${requestScope.student.name}</h1>
            <input style="display: none" type="text" name="sid"  value="${requestScope.student.id}"/>
            <table>
                <tbody>

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
        </form>

        <table border="1">
            <tbody>
                <tr>
                    <td></td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>${d.toString()} <br/>${helper.getDayNameOfWeek(d)}</td>
                        </c:forEach>
                </tr>
                <c:forEach items="${requestScope.slots}" var="s">
                    <tr>
                        <td>${s.name} ${s.description}</td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ss">
                                    <c:if test="${helper.isEqual(d, ss.date) == 1 and ss.timeslot.id eq s.id}">
                                        <a href="#">${ss.group.subject.name}</a><br/>
                                        at ${ss.room.name} <br/>
                                        (${ss.isAttended()}) <br/>
                                        ${s.description}
                                    </c:if>
                                </c:forEach>                                       
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
