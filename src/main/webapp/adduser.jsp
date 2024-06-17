<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Student Register form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #D7F2E3;
        }
        .error {
            display: none;
        }
        .small-sweetalert .swal2-popup {
            width: 50px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <div class="main_contents">
        <div id="sub_content">
            <form class="row g-3 mt-3 ms-2" action="AddUserServlet" method="post"  >
                <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Registration</h2>

                <% String successMessage = (String) request.getAttribute("successMessage");
                    if (successMessage != null) { %>
                    <script>
                        Swal.fire({
                            icon: 'success',
                            title: 'Success',
                            text: '<%= successMessage %>',
                            customClass: {
                                popup: 'small-sweetalert'
                            }
                        });
                    </script>
                <% } %>  
                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="id" class="col-md-2 col-form-label">User Id</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="id" name="id">
                    </div>
                </div>
                

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="name" class="col-md-2 col-form-label">User Name</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="name" name="name">
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="email" class="col-md-2 col-form-label">Email</label>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="email" name="email">
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="password" class="col-md-2 col-form-label">Password</label>
                    <div class="col-md-4">
                        <input type="password" class="form-control" id="password" name="password">
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <label for="userRole" class="col-md-2 col-form-label">User Role</label>
                    <div class="col-md-4">
                        <select class="form-control" id="role" name="role">
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <button type="reset" class="btn btn-danger">Reset</button>
                        <button type="submit" class="btn btn-danger">Register</button>
                        <a class="btn btn-danger" href="login.jsp">Cancel</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function validateForm() {
            var name = document.getElementById("name").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
           
            var userRole = document.getElementById("userRole").value;

            if (name.trim() == "") {
                showError("User Name is required");
                return false;
            }

            if (email.trim() == "") {
                showError("Email is required");
                return false;
            } else if (!email.includes("@")) {
                showError("Invalid email address");
                return false;
            }

            if (password.trim() == "") {
                showError("Password is required");
                return false;
            }

            

            if (userRole.trim() == "") {
                showError("User Role is required");
                return false;
            }

            return true;
        }

        function showError(message) {
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: message,
                customClass: {
                    popup: 'small-sweetalert'
                }
            });
        }
    </script>
</body>
</html>
