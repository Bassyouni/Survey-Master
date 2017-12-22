<%-- 
    Document   : Statistics.jsp
    Created on : Dec 14, 2017, 3:20:04 AM
    Author     : cdc
--%>

<%@page import="Model.Answer"%>
<%@page import="Model.Survey"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  
    //this is the way to get the session.
    HttpSession currentUserSession = request.getSession();
    
    String userName = "";
    String userId = "";
    String SurveyId = "";
    
    if(currentUserSession.getAttribute("id") != null)
    {
        userName = currentUserSession.getAttribute("name").toString();
        userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
        userId = currentUserSession.getAttribute("id").toString();
        SurveyId = request.getParameter("surveyId");
    }
    else
    {
        //session has ended
        response.sendRedirect("Login.jsp");
        return;
    }
    
    HashMap<String, String> attributeMap = new HashMap<String,String>();
    attributeMap.put("id", SurveyId);
    Survey currentSurvey = Survey.getSurvey(attributeMap);
    
    attributeMap.clear();
    attributeMap.put("survey_id", SurveyId);
    int anonymosUsers = Answer.getAnonymosUsers(attributeMap);
    int knownUsers = Answer.getKnownUsers(attributeMap);
    System.out.println(knownUsers);
    String numberOfPeopleWhoTookThisSurvey = String.valueOf(currentSurvey.getSurveyCount());
    String numberOfPeopleWhoReportedThisSurvey = String.valueOf(currentSurvey.getNumberOfReports());
%>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Statistics</title>
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
        <header class="masthead" style="background-image: url('img/statistics.jpg')">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                  <h1>Statistics</h1>
                  <span class="subheading">Here you can see !</span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            
            
        <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="post-preview">
            <a>
              <h2 class="post-title">
                  <%= currentSurvey.getName() %>
              </h2>
            </a>
              <h3 class="post-subtitle text-success">
                Number of People who took this survey: <%= numberOfPeopleWhoTookThisSurvey %>
              </h3>

            <p class="post-meta">Posted by
                <a href="#"><%= userName %></a>
              </p>
          </div>
          <hr>
          
          <div class="post-preview">
            <a >
              <h2 class="post-title">
                  <%= currentSurvey.getName() %>
              </h2>
            </a>
              <h3 class="post-subtitle text-warning">
                Number of People who reported this survey: <%= numberOfPeopleWhoReportedThisSurvey %>
              </h3>

            <p class="post-meta">Posted by
                <a href="#"><%= userName %></a>
              </p>
          </div>
              
          <div id="piechart"></div>
          
        </div>
      </div>
    </div>
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
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
            // Load google charts
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            // Draw the chart and set the chart values
            function drawChart() {
              var data = google.visualization.arrayToDataTable([
              ['users', 'anonymos vs known'],
              ['KnownUser', <%= knownUsers %>],
              ['AnonymosUser', <%= anonymosUsers %>]
            ]);

              // Optional; add a title and set the width and height of the chart
              var options = {'title':'Anonymos vs Known', 'width':550, 'height':400};

              // Display the chart inside the <div> element with id="piechart"
              var chart = new google.visualization.PieChart(document.getElementById('piechart'));
              chart.draw(data, options);
            }

        </script>
    </body>
</html>
