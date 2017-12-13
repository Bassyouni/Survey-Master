<%-- 
    Document   : AddAdmin
    Created on : Dec 13, 2017, 5:45:45 PM
    Author     : cdc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Sanad">
        
        <title>AddAdmin</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- Custom styles for this template -->
        <link href="css/clean-blog.min.css" rel="stylesheet">
        
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
          <div class="container">
              <a class="navbar-brand" href="AdminSurveyFeed.jsp">Survey Master</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              Menu
              <i class="fa fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link" href="AdminSurveyFeed.jsp">Survey Feed</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="AdminUserFeed.jsp">User Feed</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="AddAdmin.jsp">Add Admin</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="logout">Log Out</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
         
        <!-- Page Header -->
        <header class="masthead" style="background-image: url('img/Admin-wallpaper.jpg')">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                  <h1>Admin Panel</h1>
                  <span class="subheading">Add Admin</span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <!-- Main Content -->
    <div class="container">
        <% 
            HttpSession currentUserSession = request.getSession();
            if(currentUserSession.getAttribute("added") != null){
                if(currentUserSession.getAttribute("added").equals("false")){
                    out.print("<p class='text-center text-danger wrong-note'>Name Or E-mail already Exists!!</p>");
                    currentUserSession.setAttribute("added", null);
                }
            }
            
        %>
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <form name="sentMessage" id="contactForm" action="AddAdmin" method="Post">
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>User Name</label>
                <input name="userName" type="text" class="form-control" placeholder="Username..." id="user-name" required data-validation-required-message="Please enter Admin username">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>E-mail</label>
                <input name="email" type="email" class="form-control" placeholder="E-mail..." id="user-name" required data-validation-required-message="Please enter Admin E-mail">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                  <label id="new-password-label">Password</label>
                  <input name="password" type="password" class="form-control" placeholder="password..." id="new-password" required data-validation-required-message="Please enter Admin password" minlength="8" >
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group col-xs-12 floating-label-form-group controls">
                <label id="Confirmation-label">Confirm Password</label>
                <input name="confirmationPassword" type="password" class="form-control" placeholder="Confirm Here..." id="confirmation" required data-validation-required-message="Please Confirm password" minlength="8">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <br>
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="sendMessageButton">Add</button>
            </div>
          </form>
        </div>
      </div>
    </div>

    <hr>

    <!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <ul class="list-inline text-center">
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="#">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
            </ul>
            <p class="copyright text-muted">Copyright &copy; Your Website 2017</p>
          </div>
        </div>
      </div>
    </footer>
        
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Custom scripts for this template -->
        <script src="js/clean-blog.min.js"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="js/change-password.js" type="text/javascript"></script>
    </body>
</html>

