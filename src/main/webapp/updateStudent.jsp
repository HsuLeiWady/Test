<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<style>
html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            overflow-x: hidden;
        }
        .container {
            padding-top: 20px;
            padding-bottom: 20px;
        }
</style>
<body>
	<%@ include file="header.html" %>
<div class="container mt-10">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <form action="updateStudent" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
               <input type="hidden" name="id" value="${student.id}" />
                <h2 class="mb-4">Student update</h2>

                <div class="mb-3 row">
                    <label for="name" class="col-md-4 col-form-label">Name</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="name" name="name" value="${student.name}" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="dob" class="col-md-4 col-form-label">DOB</label>
                    <div class="col-md-8">
                        <input type="date" class="form-control" id="dob" name="dob" value="${student.DOB}" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="gender" class="col-md-4 col-form-label">Gender</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="gender" name="gender" value="${student.gender}" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="phone" class="col-md-4 col-form-label">Phone</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="phno" name="phno" value="${student.phno}" required>
                    </div>
                </div>

                <div class="mb-3 row">
                    <label for="education" class="col-md-4 col-form-label">Education</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="education" name="education" value="${student.education}" required>
                    </div>
                </div>
           <div class="mb-3 row">
            <label for="education" class="col-md-4 col-form-label">Courses</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="attend" name="attend" value="${student.attend}" required>
                    </div>     
                    </div>
                <div class="mb-3 row">
                    <label for="photo" class="col-md-4 col-form-label">Photo</label>
                       <div class="col-md-8">
                        <input type="file" class="form-control" id="photo" name="photo" value="${student.photo}" required>
                    </div>

                </div>

                <div class="mb-3 row">
                    <div class="col-md-8 offset-md-4">
                       <input type="submit" value="Update" class="btn btn-primary"/>
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
                                <h5 style="color: rgb(127, 209, 131);">Updated Successfully!</h5>
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
