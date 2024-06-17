<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">
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
                <form action="UpdateUserServlet" method="post">
                    <input type="hidden" name="id" value="${user.id}" />
                    <div class="mb-3 row">
                        <label for="name" class="col-md-4 col-form-label">Name</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" id="name" name="name" value="${user.name}" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="email" class="col-md-4 col-form-label">Email</label>
                        <div class="col-md-8">
                            <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="password" class="col-md-4 col-form-label">Password</label>
                        <div class="col-md-8">
                            <input type="password" class="form-control" id="password" name="password" value="${user.password}" required>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-md-8 offset-md-4">
                            <input type="submit" value="Update" class="btn btn-primary"/>
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
