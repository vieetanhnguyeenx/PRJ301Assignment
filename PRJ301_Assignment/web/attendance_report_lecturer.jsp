<%-- 
    Document   : attendance_report_lecturer
    Created on : Nov 2, 2022, 9:32:00 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="helper" class="helper.DateTimeHelper"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>TERM</th>
                    <th>COURSE</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <c:forEach items="${requestScope.term}" var="t">
                            <a href="attendance_report_for_lecture?lid=${requestScope.lid}&termid=${t.tearmId}&gid=-1">${t.semester.semesterName} ${t.year.yearName}</a> <br/>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${requestScope.group}" var="g">
                            <a href="attendance_report_for_lecture?lid=${requestScope.lid}&termid=${g.term.tearmId}&gid=${g.id}">${g.subject.fullName} (${g.subject.name})(${g.name} start ${g.startDate} )
                            </a>
                            <br/>
                        </c:forEach>
                    </td>
                </tr>
            </tbody>
        </table>

        <table border="1">
            <thead>
                <tr>
                    <th></th>
                        <c:forEach items="${requestScope.date}" var="d">
                        <th>${d.date} Slot ${d.index}</th>
                        </c:forEach>
                </tr>
            </thead>
            <tbody>
                
                    <c:forEach items="${requestScope.student}" var="s">
                        <tr>
                            <td>${s.name} ${s.id}</td>
                            <c:forEach items = "${s.attandances}" var="a">
                                    <td>${a.isPresentString()}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>


    </body>
</html>
