<%-- 
    Document   : AdminSendMail
    Created on : Dec 14, 2017, 3:41:21 PM
    Author     : cdc
--%>

<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <%  
        //this is the way to get the session.
        HttpSession currentUserSession = request.getSession();
        String targetUserId = request.getParameter("userId");
        User targetUser = null;
        if(targetUserId == null){
            response.sendRedirect("AdminUserFeed.jsp");
        }
        else if(targetUserId.equals("all")){
            
        }
        else{
            targetUser = User.selectUser(targetUserId);
        }
        
        String userName = "";
        String userId = "";
        
        if(currentUserSession.getAttribute("id") != null)
        {
            userName = currentUserSession.getAttribute("name").toString();
            userId = currentUserSession.getAttribute("id").toString();
        }
        else
        {
            //session has ended
            response.sendRedirect("Login.jsp");
            return;
        }
    %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Sanad">
        
        <title>Send E-mail</title>

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
                  <a class="nav-link" href="AdminReportFeed.jsp">Reports</a>
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
                  <span class="subheading">Contact, <% if(targetUser == null){out.print("All");} else{out.print(targetUser.getName());} %></span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <!-- Main Content -->
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <%
                if(targetUser == null){
                    out.print("<form name='sentMessage' id='contactForm' action='SendMailServlet?targetUserMail=all' method='Post'>");
                }
                else{
                    out.print("<form name='sentMessage' id='contactForm' action='SendMailServlet?targetUserMail=" + targetUser.getEmail() + "' method='Post'>");
                }
            %>
          
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                <label>Subject</label>
                <input name="subject" type="text" class="form-control" placeholder="Subject" required data-validation-required-message="Please enter The subject" minlength="2">
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <div class="control-group">
              <div class="form-group floating-label-form-group controls">
                  <label id="new-password-label" for="message">Message</label>
                  <textarea name="message" class="form-control" required data-validation-required-message="Please enter your Message" rows="5" minlength="15">
                    Your message...
                  </textarea>
                <p class="help-block text-danger"></p>
              </div>
            </div>
            <br>
            <div id="success"></div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary" id="sendMessageButton">Send</button>
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
