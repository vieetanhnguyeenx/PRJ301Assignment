<%-- 
    Document   : student_home
    Created on : Nov 7, 2022, 8:47:40 PM
    Author     : Admin
--%>

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
        <h1>FPT University Academic Portal</h1>
        <hr style="border-top: 1px solid black;">
        <div class="p-3 mb-2 bg-secondary text-white">
            <a class="btn btn-success" href="#" role="button">${sessionScope.account.username}</a>
            <a class="btn btn-success" href="logout" role="button">Logout</a>
        </div>
        <div style="margin-top: 20px;">
            <a class="p-3 mb-2 bg-primary text-white" href="scheduleofweek">ScheduleOfWeek</a>
            <a class="p-3 mb-2 bg-primary text-white" href="attendance_report">ViewAttendstudent</a>
        </div>
    </div>




    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>
