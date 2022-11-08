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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div>
                <h2>FPT University Academic Portal</h2>
                <hr style="border-top: 1px solid black;">
            </div>
            <div class="p-3 mb-2 bg-secondary bg-gradient text-white">
                <div class="row">
                    <div class="col">
                        <a class="btn btn-success" style="margin-right: 10px ;" href="student_home" role="button">Home</a>
                        <h5 style="display: inline ;">View Schedule</h5>
                    </div>
                    <div class="col d-flex justify-content-end">
                        <a class="btn btn-success" style="margin-right: 10px ;" href=" # " role="button ">${sessionScope.account.username}</a>
                        <a class="btn btn-success " href="logout " role="button ">Logout</a>
                    </div>
                </div>

            </div>

            <h3>View attendance for ${requestScope.student.login} (${requestScope.student.name})</h3>
            <div class="row">
                <div class="col">Select a campus/program, term, course ...</div>
            </div>

            <div style="margin-bottom: 30px">
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
            </div>

            <div class="row">
                <div class="col">... then see report</div>
            </div>

            <table class="table" border="1">
                <thead>
                    <tr>
                        <th scope="col">NO</th>
                        <th scope="col">DATE</th>
                        <th scope="col">SLOT</th>
                        <th scope="col">ROOM</th>
                        <th scope="col">LECTURER</th>
                        <th scope="col">GROUP NAME</th>
                        <th scope="col">ATTEDANCE STATUS</th>
                        <th scope="col">LECTURER'S COMMENT</th>
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
        </div>
         

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
