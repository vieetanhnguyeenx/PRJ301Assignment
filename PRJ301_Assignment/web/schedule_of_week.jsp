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
                        <a class="btn btn-success " href="logout" role="button ">Logout</a>
                    </div>
                </div>

            </div>


            <form action="scheduleofweek" method="GET">
                <h3> Activities for ${requestScope.student.name}</h3>
                <div class="row ">
                    <div class="col ">Note: These activities do not include extra-curriculum activities, such as club activities ...</div>
                </div>
                <div class="row ">
                    <div class="col ">Chú thích: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa, ví dụ như hoạt động câu lạc bộ ...</div>
                </div>
                <input style="display: none" type="text" name="sid"  value="${requestScope.student.id}"/>
                <table style="margin-bottom: 20px">
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
                                <input class="btn btn-primary" type="submit" value="View">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <table border="1" class="table table-striped w-auto">
                <tbody>
                    <tr>
                        <td></td>
                        <c:forEach items="${requestScope.dates}" var="d">
                            <td>${d.toString()} <br/>${helper.getDayNameOfWeek(d)}</td>
                            </c:forEach>
                    </tr>
                    <c:forEach items="${requestScope.slots}" var="s">
                        <tr>
                            <td><samp> ${s.name} ${s.description} </samp></td>
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
        </div>






        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js " integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo " crossorigin="anonymous "></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js " integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1 " crossorigin="anonymous "></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js " integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM " crossorigin="anonymous "></script>
    </body>
</html>
