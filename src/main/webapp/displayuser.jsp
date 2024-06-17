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
<div class="main_contents">
    <h1>User Management</h1>
    <table id="userTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>User Email</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                 <td>
  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModalCenter" data-user-id="${user.id}">
    Delete
</button>

                 
                        <form action="UpdateUserServlet" method="get" style="display:inline;">
                             <input type="hidden" name="id" value="${user.id}" />
                             <button type="submit" class="btn btn-info">Update</button>
                                </form>
                            </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
  Launch demo modal
</button>

<!-- Modal -->
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <h2 style="color: red;">Are You Sure Want to delete</h2>
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
    // Initialize DataTable
    $('#userTable').DataTable();

    // Handle opening the modal and setting the user ID for deletion
    $('#exampleModalCenter').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var userId = button.data('user-id'); // Extract info from data-* attributes
        
        // Update the modal's delete button href attribute with the user ID
        var deleteButton = $('#confirmDeleteButton');
        deleteButton.attr('href', 'DeleteUserServlet?id=' + userId);
    });
});
</script>

</body>
</html>

