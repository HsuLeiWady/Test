
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<%@ include file="header.html" %>
<div class="container mt-10">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <form action="addstudents" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
                <h2 class="mb-4">Student Registration</h2>

              <!--   <div class="mb-3 row">
                    <label for="studentID" class="col-md-4 col-form-label">Student ID</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="studentID" name="studentId" value="STU" required>
                    </div>
                </div> -->

                <div class="mb-3 row">
                    <label for="name" class="col-md-4 col-form-label">Name</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="name" placeholder="Enter your name" name="name" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="dob" class="col-md-4 col-form-label">DOB</label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" id="dob" placeholder="Enter your Date of Birth" name="dob" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label class="col-md-4 col-form-label">Gender</label>
                    <div class="col-md-8">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="Male" checked required>
                            <label class="form-check-label" for="male">Male</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="female" value="Female" required>
                            <label class="form-check-label" for="female">Female</label>
                        </div>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="phone" class="col-md-4 col-form-label">Phone</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="phno" placeholder="Enter your phone number" name="phno" value="09" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="education" class="col-md-4 col-form-label">Education</label>
                    <div class="col-md-8">
                        <select class="form-control" id="education" name="education" required>
                            <option value="Bachelor of Information Technology" selected>Bachelor of Information Technology</option>
                            <option value="Diploma in IT">Diploma in IT</option>
                            <option value="Bachelor of Computer Science">Bachelor of Computer Science</option>
                        </select>
                    </div>
                </div>
           <div class="mb-3 row">
    <label for="education" class="col-md-4 col-form-label">Courses:</label>
    <div class="col-md-8">
        <div class="form-check">
            <c:forEach items="${courses}" var="course">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="attend" value="${course.name}" id="course${course.id}">
                    <label class="form-check-label" for="course${course.id}">
                        ${course.name}
                    </label>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
                <div class="mb-3 row">
                    <label for="photo" class="col-md-4 col-form-label">Photo</label>
                    <div class="col-md-8">
                        <input type="file" class="form-control" id="photo" name="photo">
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <button type="reset" class="btn btn-danger">Reset</button>
                      <input type="button" class="btn btn-secondary me-2" value="All Students" onclick="location.href='DisplayStudentServlet';">
                        <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">Add</button>
                    </div>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Student Registration</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <h5 style="color: rgb(127, 209, 131);">Registered Successfully!</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-success" data-bs-dismiss="modal">Ok</button>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger mt-3">${error}</div>
                </c:if>
            </form>
        </div>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>
