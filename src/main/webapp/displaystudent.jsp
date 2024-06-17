<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css">

</head>
<body>
<%@ include file="header.html" %>
<div class="container mt-10">
    <div class="row">
        <div class="col-md-8 offset-md-2">

            <table id="studentTable" class="table table-striped">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Name</th>
                        <th>Dob</th>
                        <th>Gender</th>
                        <th>Phno</th>
                        <th>Education</th>
                        <th>Course</th> 
                        <th>Photo</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="student" items="${students}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${student.name}</td>
                            <td>${student.dob}</td>
                            <td>${student.gender}</td>
                            <td>${student.phno}</td>
                            <td>${student.education}</td>
                            <td>${student.attend}</td>
                            <td><img src="data:image/jpeg;base64,${student.photo}" alt="StudentImage" style="max-width: 150px; max-height: 150px;"></td>
                          <td>
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" data-student-id="${student.id}">
        Delete
    </button> 
    <form action="updateStudent" method="get" style="display:inline;">
        <input type="hidden" name="studentId" value="${student.id}" />
        <button type="submit" class="btn btn-info">Update</button>
    </form>
</td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModalLabel">Confirm Deletion</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p style="color: red;">Are you sure you want to delete this student?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <a id="confirmDeleteButton" href="#" class="btn btn-danger">Delete</a>
            </div>
        </div>
    </div>
</div>
    

    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        $('#studentTable').DataTable();

        // Handle opening the modal and setting the student ID for deletion
        $('#deleteModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var studentId = button.data('student-id'); // Extract info from data-* attributes

            // Update the modal's delete button href attribute with the student ID
            var deleteButton = $('#confirmDeleteButton');
            deleteButton.attr('href', 'DeleteStudentServlet?id=' + studentId);
        });
    });
</script>

</body>
</html>
