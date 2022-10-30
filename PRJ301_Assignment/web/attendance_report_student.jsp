<%-- 
    Document   : attendance_report_student
    Created on : Oct 28, 2022, 4:07:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <a href="attendance_report?stid=${requestScope.studentid}&termid=${t.tearmId}&gid=-1">${t.semester.semesterName} ${t.year.yearName}</a> <br/>
                        </c:forEach>
                    </td>


                    <td>
                        <c:forEach items="${requestScope.group}" var="g">
                            <a href="attendance_report?stid=${requestScope.studentid}&termid=${g.term.tearmId}&gid=${g.id}">${g.subject.fullName} (${g.subject.name})(${g.name} start ${g.startDate} )
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
                        <th>NO</th>
                        <th>DATE</th>
                        <th>SLOT</th>
                        <th>ROOM</th>
                        <th>LECTURER</th>
                        <th>GROUP NAME</th>
                        <th>ATTEDANCE STATUS</th>
                        <th>LECTURER'S COMMENT</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.attend}" var="a">
                        <tr>
                            <td>${a.session.index}</td>
                            <td>${a.session.date}</td>
                            <td>${a.session.timeslot.id} ${a.session.timeslot.description}</td>
                            <td>${a.session.room.name}</td>
                            <td>${a.session.lecturer.name}</td>
                            <td>${a.session.group.name}</td>
                            <td>${a.isPresentString()}</td>
                            <td>${a.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

    </body>
</html>
