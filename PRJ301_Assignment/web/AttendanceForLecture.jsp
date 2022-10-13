<%-- 
    Document   : AttendanceForLecture
    Created on : Oct 14, 2022, 1:18:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <title>Document</title>
</head>
<body>
    <h1>Single activity Attendance</h1>
    <table class="table">
        <thead>
            <tr>
                <th scope="col">No</th>
                <th scope="col">Group</th>
                <th scope="col">Code</th>
                <th scope="col">Name</th>
                <th scope="col">Image</th>
                <th scope="col">Attendance</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>HE1643</td>
                <td>HE166666</td>
                <td>Nguyen Van A</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_1" value="present"> Present
                    <input type="radio" name="atten_1" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>2</td>
                <td>HE1643</td>
                <td>HE166667</td>
                <td>Nguyen Van B</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_2" value="present"> Present
                    <input type="radio" name="atten_2" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>3</td>
                <td>HE1643</td>
                <td>HE166668</td>
                <td>Nguyen Van C</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_3" value="present"> Present
                    <input type="radio" name="atten_3" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>4</td>
                <td>HE1643</td>
                <td>HE166669</td>
                <td>Nguyen Van D</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_4" value="present"> Present
                    <input type="radio" name="atten_4" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>5</td>
                <td>HE1643</td>
                <td>HE166670</td>
                <td>Nguyen Van E</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_5" value="present"> Present
                    <input type="radio" name="atten_5" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>6</td>
                <td>HE1643</td>
                <td>HE166670</td>
                <td>Nguyen Van E</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_6" value="present"> Present
                    <input type="radio" name="atten_6" value="absent"> Absent
                </td>
            </tr>

            <tr>
                <td>7</td>
                <td>HE1643</td>
                <td>HE166670</td>
                <td>Nguyen Van E</td>
                <td><img src="#" alt="img"></td>
                <td>
                    <input type="radio" name="atten_7" value="present"> Present
                    <input type="radio" name="atten_7" value="absent"> Absent
                </td>
            </tr>
        </tbody>
        
    </table>
    <input class="btn btn-primary" type="submit" value="Submit">
</body>
</html>
