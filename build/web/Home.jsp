<%-- 
    Document   : Home
    Created on : Dec 5, 2017, 3:48:24 AM
    Author     : Bassyouni
--%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Survey"%>
<%@page import="database.DatabaseConnection"%>
<%@page import="Model.Router"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Sanad">

    <title>Home</title>

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
        <%
            Router router = new Router();
            HttpSession userSession = request.getSession();
            
            String name = "";
            String id = "";
            
            if(userSession.getAttribute("id") != null)
            {
                name = userSession.getAttribute("name").toString();
                id = userSession.getAttribute("id").toString();
            }
            else
            {
                //session has ended
                response.sendRedirect("Login.jsp");
                return;
            }
        %>
        
        <script type="text/javascript">
            var Msg ='<%=request.getAttribute("surveyReported")%>';
                if (Msg != "null") 
                {
                    function alertName()
                    {
                        alert("Survey is Reported!");
                    } 
                }
        </script>
        
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
                  <a class="nav-link" href="Profile.jsp">Profile</a>
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
        <header class="masthead" style="background-image: url('img/home-bg.jpg')">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                  <h1>Survey Master</h1>
                  <span class="subheading">Making it simple...</span>
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
                currentSurvey.getAllQuestions();
                
//                if(currentSurvey.getOwnerId() == Integer.getInteger(id))
//                    continue;
                
                output = "<div class='post-preview'>" +
              "<a href='takeSurvey.jsp?id=" + currentSurvey.getId() + "&surveyName=" + currentSurvey.getName() + "'>"+
                "<h2 class='post-title'>"+
                  currentSurvey.getName() +
                "</h2>" +
                "<h3 class='post-subtitle'>"+
                  "Number Of Questions: "+ currentSurvey.questions.size() +
                "</h3>"+
              "</a>"+
              "<p class='post-meta'>Posted by: " + 
                "<a>" + currentSurvey.getOwnerId() + "</a>"+
                
              "</p>" + 
            "</div>"+
            "<hr>";
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
        <script type="text/javascript"> window.onload = alertName; </script>
    </body>
</html>
