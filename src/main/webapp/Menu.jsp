<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
  crossorigin="anonymous">

<link rel="stylesheet"
  href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
  src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
  crossorigin="anonymous"></script>
<title>Dropdown</title>
</head>
<style>
body {
  margin: 0px;
  font-family: "Roboto", sans-serif;
  font-size: 11pt;
  float: left;
  text-align: justify;
  color: black; /* Change font color to black */
  background-image: linear-gradient(45deg, #dde6e5, #d6f5e3);
  margin-left: auto;
  margin-right: auto;
  width: 1366px;
  height: 100%;
  overflow: hidden;
}

#testheader {
  background: #f2f2f2;
  width: 100%;
  height: 90px;
  float: top;
  padding: 20px 20px 0px 0px;
}

#testheader a {
  /* text-decoration: none; */
  color: rgb(114, 112, 112);
  font-family: "Roboto", sans-serif;
}

#testsidebar {
  background: rgb(255, 217, 171);
  float: left;
  width: 220px;
  min-height: 1000px;
  height: auto !important;
}

#testfooter {
  background: #f2f2f2;
  height: 60px;
  text-align: center;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 20px 20px 0px 0px;
  position: fixed;
  width: 100%;
  float: bottom;
}

#container {
  overflow: hidden;
  flex: 1;
}

#main_contents {
  background: #ffffff;
  overflow: hidden;
  min-height: 1000px;
  height: auto !important;
  padding-top: 15px;
}

#sub_content {
  padding: 10px;
  margin-left: auto;
  padding-left: 10px;
  overflow: auto;
  position: absolute;
  width: 100%;
  height: auto;
  top: 90px;
  bottom: 20px;
  left: 220px;
}

#contents h3 {
  color: rgb(82, 179, 69);
  padding: 20px 20px 20px 20px;
  margin-left: 250px;
  font-size: 20px;
}

#stduentTable {
  width: 80%;
}

/* Login Form css */
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}

.login-page p {
  color: firebrick;
  font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande',
    'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
}

.login-page .form .login {
  margin-top: -31px;
  margin-bottom: 26px;
}

.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
    rgba(0, 0, 0, 0.24);
}

.form input {
  font-family: "Roboto", sans-serif;
  outline: 0;
  background: #f2f2f2;
  width: 100%;
  border: 0;
  margin: 0 0 15px;
  padding: 15px;
  box-sizing: border-box;
  font-size: 14px;
}

.form button {
  font-family: "Roboto", sans-serif;
  text-transform: uppercase;
  outline: 0;
  background-color: #328f8a;
  background-image: linear-gradient(45deg, #328f8a, #08ac4b);
  width: 100%;
  border: 0;
  padding: 15px;
  color: #FFFFFF;
  font-size: 14px;
  -webkit-transition: all 0.3 ease;
  transition: all 0.3 ease;
  cursor: pointer;
}

.form .message {
  margin: 15px 0 0;
  color: #b3b3b3;
  font-size: 12px;
}

.form .message a {
  color: #4CAF50;
  text-decoration: none;
}

.login-page-body {
  background-color: #328f8a;
  background-image: linear-gradient(45deg, #328f8a, #08ac4b);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100%;
  overflow: hidden;
  color: rgb(0, 0, 0);
}
/* Fixed sidenav, full height */
.sidenav {
  height: 100%;
  width: 200px;
  position: absolute;
  z-index: 0;
  left: 0;
  font-family: "Roboto", sans-serif;
  /* background-color: rgb(154, 255, 133); */
  background-image: linear-gradient(45deg, #328f8a, #08ac4b);
  overflow-x: hidden;
  padding-top: 20px;
  float: left;
}

/* Style the sidenav links and the dropdown button */
.sidenav a, .dropdown-btn {
  padding: 6px 8px 6px 16px;
  text-decoration: none;
  font-size: 16px;
  font-family: "Roboto", sans-serif;
  color: #ffffff;
  display: block;
  border: none;
  background: none;
  width: 100%;
  text-align: left;
  cursor: pointer;
  outline: none;
}

/* On mouse-over */
.sidenav a:hover, .dropdown-btn:hover {
  color: #000000;
}

/* Main content */
.main {
  margin-left: 200px; /* Same as the width of the sidenav */
  font-size: 20px; /* Increased text to enable scrolling */
  padding: 0px 10px;
}

/* Add an active class to the active dropdown button */
.active {
  background-color: rgb(145, 145, 145);
  color: white;
}

/* Dropdown container (hidden by default). Optional: add a lighter background color and some left padding to change the design of the dropdown content */
.dropdown-container {
  display: none;
  background-color: rgb(145, 145, 145);
  padding-left: 8px;
}

/* Optional: Style the caret down icon */
.fa-caret-down {
  float: right;
  padding-right: 8px;
}

/* Some media queries for responsiveness */
@media screen and (max-height: 450px) {
  .sidenav {
    padding-top: 15px;
  }
  .sidenav a {
    font-size: 18px;
  }
}
</style>
</head>
<body>
  <%@ page import="java.util.Date"%>
  <%
  Date date = new Date();
  %>
  <div id="testheader">
    <div class="container">
      <div class=row>
        <div class="col-md-5 ">
        <c:if test="${loginUser.userRole eq 'user'}">
          <a href="studentReg">
            <h3>Student Registration</h3>
          </a>
          </c:if>
          <c:if test="${loginUser.userRole eq 'Admin'}">
          <a href="#">
            <h3>Admin Management</h3>
          </a>
          </c:if>
        </div>
        <div class="col-md-6">
          <p>User: ${loginUser.userId} ${sessionScope.loginUserName}</p>
          <p>
            Current Date :
            <%=date%>
          </p>
        </div>
        <div class="col-md-1">
          <button class="nav-link-button" onclick="location.href='logout'">Logout</button>
        </div>
      </div>
    </div>
  </div>

  <div class="sidenav">

    <button class="dropdown-btn">
      Class Management <i class="fa fa-caret-down"></i>
    </button>

    <div class="dropdown-container">
      <c:if test="${loginUser.userRole eq 'Admin'}">
      <a href="courseReg">Course Registration </a> 
      </c:if>
      <c:if test="${loginUser.userRole eq 'user'}">
      <a href="studentReg">Student Registration </a> 
      </c:if>
      <a href="student">Student Search</a>
    </div>
    <c:if test="${loginUser.userRole eq 'Admin'}">
    <a href="user">Users Management</a>
    </c:if>
    <a href="userProfile">User Profile</a>
  </div>

  <script>
    /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
    var dropdown = document.getElementsByClassName("dropdown-btn");
    var i;

    for (i = 0; i < dropdown.length; i++) {
      dropdown[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var dropdownContent = this.nextElementSibling;
        if (dropdownContent.style.display === "block") {
          dropdownContent.style.display = "none";
        } else {
          dropdownContent.style.display = "block";
        }
      });
    }
  </script>
</body>
</html>