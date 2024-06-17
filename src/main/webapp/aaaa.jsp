<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Student</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2>Update Student</h2>
        <form action="updateStudent" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${student.id}" />
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${student.name}" required>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth</label>
                <input type="date" class="form-control" id="dob" name="dob" value="${student.DOB}" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender</label>
                <input type="text" class="form-control" id="gender" name="gender" value="${student.gender}" required>
            </div>
            <div class="form-group">
                <label for="phno">Phone Number</label>
                <input type="text" class="form-control" id="phno" name="phno" value="${student.phno}" required>
            </div>
            <div class="form-group">
                <label for="education">Education</label>
                <input type="text" class="form-control" id="education" name="education" value="${student.education}" required>
            </div>
            <div class="form-group">
                <label for="attend">Attendance</label>
                <input type="text" class="form-control" id="attend" name="attend" value="${student.attend}" required>
            </div>
            <div class="form-group">
                <label for="photo">Photo</label>
                <input type="file" class="form-control" id="photo" name="photo">
            </div>
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</body>
</html>