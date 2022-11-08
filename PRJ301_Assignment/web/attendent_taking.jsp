<%-- 
    Document   : attendent_taking
    Created on : Oct 24, 2022, 9:20:45 PM
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
                        <a class="btn btn-success" style="margin-right: 10px ;" href="lecturer_home" role="button">Home</a>
                        <h5 style="display: inline ;">Attendent Taking</h5>
                    </div>
                    <div class="col d-flex justify-content-end">
                        <a class="btn btn-success" style="margin-right: 10px ;" href=" # " role="button ">${sessionScope.account.username}</a>
                        <a class="btn btn-success " href="logout" role="button ">Logout</a>
                    </div>
                </div>

            </div>

            Take attendance for Group: ${requestScope.ses.group.name} <br/>
            Subject: ${requestScope.ses.group.subject.name} <br/>
            Room: ${requestScope.ses.room.name}<br/>
            Date: ${requestScope.ses.date} - ${requestScope.ses.timeslot.name} <br/>
            Attended: <span style="color: red;"> ${requestScope.ses.attandated?"Yes":"No"} </span>

            <form action="attendent_taking" method="POST">
                <input type="hidden" name="sesid" value="${param.id}"/>
                <table class="table table-striped" style="margin-top: 10px;">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>StudentID</th>
                            <th>Full Name</th>
                            <th>Image</th>
                            <th>Present</th>
                            <th>Absent</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.ses.attandances}" var="a" varStatus="loop">
                            <tr>
                                <td>${loop.index+1}</td>
                                <td>${a.student.id}
                                    <input type="hidden" name="stdid" value="${a.student.id}"/>
                                </td>
                                <td>${a.student.name}</td>
                                <td> 
                                    <img src="${a.student.imgUrl}">
                                </td>
                                <td><input type="radio"
                                           <c:if test="${a.present}">
                                               checked="checked"
                                           </c:if>
                                           name="present${a.student.id}" value="present" /></td>
                                <td><input type="radio"
                                           <c:if test="${!a.present}">
                                               checked="checked"
                                           </c:if>
                                           name="present${a.student.id}" value="absent" /></td>
                                <td><input type="text" name="description${a.student.id}" value="${a.description}" /></td>
                            </tr>   

                        </c:forEach>
                    </tbody>
                </table>
                <hr style="border-top: 1px solid black;">
                <div class="d-flex justify-content-center">
                    <input class="btn btn-primary" type="submit" value="Save"/>
                </div>

                <div style="margin: 30px"></div>
            </form>

        </div>




        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js " integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo " crossorigin="anonymous "></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js " integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1 " crossorigin="anonymous "></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js " integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM " crossorigin="anonymous "></script>
    </body>
</html>
