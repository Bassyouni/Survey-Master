<%-- 
    Document   : Report.jsp
    Created on : Dec 13, 2017, 10:03:49 PM
    Author     : cdc
--%>

<%@page import="Model.Reports"%>
<%@page import="Model.User"%>
<%@page import="Model.Survey"%>
<%@page import="java.util.ArrayList"%>
<%  
    //this is the way to get the session.
    HttpSession currentUserSession = request.getSession();
    
    String userName = currentUserSession.getAttribute("name").toString();
    String userId = currentUserSession.getAttribute("id").toString();
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Report Feed</title>
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- Custom styles for this template -->
        <link href="css/clean-blog.css" rel="stylesheet">
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
                  <span class="subheading">Every Report on the system...</span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            
            
        <%
            ArrayList<Reports> reports = Reports.getAllReports();
            String output;
            for(int i = 0; i < reports.size(); i++){
                Reports currentReport = reports.get(i);
                output = "<div class='post-preview' id='user-" + currentReport.getId()+ "'>" +
              "<a>"+
                "<h2 class='post-title'>Report ID: "+
                  currentReport.getId() +
                "</h2>" +
                "<h3 class='post-subtitle'>"+
                  currentReport.getComment() + 
                "</h3>"+
              "</a>"+
              "<p class='post-meta'>";
                output += "</p>" +
                "<hr>"+
            "</div>";
                out.print(output);
            }
        %>
                </div>
            </div>
        </div>
                
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
                <p class="copyright text-muted">Copyright &copy; SurveyMaster 2017</p>
              </div>
            </div>
          </div>
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Custom scripts for this template -->
        <script src="js/clean-blog.min.js"></script>
        <script src="js/user-feed-ajax.js" type="text/javascript"></script>
        
    </body>
</html>

