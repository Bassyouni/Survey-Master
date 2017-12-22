<%-- 
    Document   : Profile
    Created on : Dec 10, 2017, 3:13:39 PM
    Author     : Sanad
--%>

<%@page import="Model.Router"%>
<%@page import="Model.Survey"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  
    //this is the way to get the session.
    
    String userName = "";
    String userId = "";
    
    HttpSession currentUserSession = request.getSession();
    
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
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title><%= userName%></title>
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
              <a class="navbar-brand" href="Home.jsp">Survey Master</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              Menu
              <i class="fa fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link" href="Home.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ChangePassword.jsp">Change Password</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AddSurvey.jsp">Add Survey</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="logout">Log Out</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        
        <!-- Page Header -->
        <header class="masthead" style="background-image: url('img/about-bg.jpg')">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                  <h1>Welcome, <%= userName %></h1>
                  <span class="subheading">Here you can manage your surveys.</span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            
            
        <%
            ArrayList<Survey> surveys = Survey.getAllSurveys();
            String output;
            for(int i = 0; i < surveys.size(); i++){
                Survey currentSurvey = surveys.get(i);
                if(currentSurvey.getOwnerId() != Integer.parseInt(userId)){
                    continue;
                }
                output = "<div class='post-preview' id='survey-" + currentSurvey.getId()+ "'>" +
              "<a href='Statistics.jsp?surveyId="+ currentSurvey.getId() + "'>"+
                "<h2 class='post-title'>"+
                  currentSurvey.getName() +
                "</h2>" +
              "</a>"+
              "<p class='post-meta'>"; 
                if(currentSurvey.isSuspended()){
                    output += "<a id='suspend-survey-" + currentSurvey.getId() +
                            "' class='text-success suspend-link'>Resume</a>"+ "   ";
                }
                else{
                    output += "<a id='suspend-survey-" + currentSurvey.getId() + 
                            "' class='text-warning suspend-link'>Suspend</a>"+ "   ";
                }
                
                output += "<a id='delete-survey-" + currentSurvey.getId() + 
                        "' class='text-danger delete-link'>Delete" + "</a>"+
              "</p>" +
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
        <script src="js/ajax-code.js"></script>
        
    </body>
</html>
