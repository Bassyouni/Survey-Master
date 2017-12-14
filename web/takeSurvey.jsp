<%-- 
    Document   : takeSurvey
    Created on : Dec 12, 2017, 5:28:20 PM
    Author     : Bassyouni
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Question"%>
<%@page import="java.util.HashMap"%>
<%@page import="Model.Survey"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Sanad">
        
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- Custom styles for this template -->
        <link href="css/clean-blog.min.css" rel="stylesheet">
        <link href="css/takeSurvey-style.css" rel="stylesheet">
        
        <%
           String id = request.getParameter("id");
           String surveyName = request.getParameter("surveyName");
       %>
        
       <title><%=surveyName%></title>
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
                  <span class="subheading"><%=surveyName%></span>
                </div>
              </div>
            </div>
          </div>
        </header>
        
        <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
        
       <%
           HashMap<String,String> selectAttributeMap = new HashMap();
           selectAttributeMap.put("id", id);
           Survey survey = Survey.getSurvey(Integer.valueOf(id), selectAttributeMap);
           survey.getAllQuestions();
           
           if (survey.isSuspended()) 
           {
               out.print("<p class='text-center text-warning wrong-note'>This Survey is Suspended!!</p>");
           }
           
           out.print("<form action=\"SaveSurveyAnswers\" id=\"" + id + "\">");
           out.print("<input type=\"hidden\" name=\"surveyId\" value=\"" + id + "\">");
           
           for(Question dummyQuestion : survey.questions)
           {
               out.print("<div class='post-preview'>");
               out.print("<h2 class='post-title'>" + dummyQuestion.getQuestion() + "</h2>");
               
               if (dummyQuestion.getType().equals(Question.MCQ))
               {
                    String givenAnswers = dummyQuestion.getGivenAnswers();
                    String[] answers = givenAnswers.split(",");
                    
                    out.print("<div class=\"radio\">");
                    for (String answer : answers)
                    {
                        out.print("<label><input type=\"radio\" name=\""+ dummyQuestion.getId() +"\" value=\"" + answer + "\" required> ");
                        out.print(answer + "</label><br>");
                    }
                    out.print("</div>");
//                   <input type="radio" name="gender" value="male"> Male<br>
//                   <input type="radio" name="gender" value="female"> Female<br>
               }
               else if (dummyQuestion.getType().equals(Question.CHECKBOXES))
               {
                   String givenAnswers = dummyQuestion.getGivenAnswers();
                   String[] answers = givenAnswers.split(",");
                   
                   out.print("<div class=\"checkbox\">");
                   for (String answer : answers)
                   {
                       out.print("<label><input type=\"checkbox\" name=\""+ dummyQuestion.getId() +"\" value=\"" + answer + "\" checked> ");
                       out.print(answer + "</label><br>");
                   }
                   out.print("</div>");
//                   <input type="checkbox" name="vehicle" value="Bike"> I have a bike<br>
//                   <input type="checkbox" name="vehicle" value="Car" checked> I have a car<br>
               }
               else if (dummyQuestion.getType().equals(Question.FREEANSWER))
               {
                   out.print("<textarea class=\"form-control\" rows=\"5\" name=\""+ dummyQuestion.getId() +"\" required></textarea>");
               }
               out.print("</div> <hr> ");
           }
           
           out.print("<div class='post-preview'>");
           out.print("<h2 class='post-title'>Do you want to show the creator your info?</h2>");
           out.print("<div class=\"radio\">");
           out.print("<label><input type=\"radio\" name=\"avail\" value=\"Anonymous\" required> Keep me Anonymous</label><br>");
           out.print("<label><input type=\"radio\" name=\"avail\" value=\"Show my Info\" required> Show my Info</label><br>");
           out.print("</div>");
           out.print("</div> <hr>");
           %>
           
           <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <%
                            if(survey.isSuspended())
                            {
                                out.print("<input disabled class='btn btn-success' type='submit' value='Submit Answers'>");
                            }
                            else
                            {
                                out.print("<input class='btn btn-success' type='submit' value='Submit Answers'>");
                            }
                        %>
                        
                    </div>

                    <div class="col-md-5">

                    </div>

                    <div class="col-md-3">
                        <button type='button' class='btn btn-danger' id='modal-btn'>Report This Survey</button>
                    </div>
                    
                </div>
            </div>
           
           </form>
                      
    </div>
            </div>
        </div> 
       
       
        <!-- The Modal -->
         <div id="myModal" class="modal">

           <!-- Modal content -->
           
           <div class="modal-content">
             <div class="modal-header">
                 <h2>Report</h2>
               <span class="close">&times;</span>
               
             </div>
             <div class="modal-body">
               <p>Please tell us why are you Reporting this Survey?</p>
               <form action="ReportSurvey" method="post">
                   <div class="form-group">
                       <textarea class="form-control" rows="3" id="comment" name="comment" minlength="18" required></textarea>
                <% 
                    out.print("<input class=\"form-control\" type=\"hidden\" name=\"surveyId\" value=\"" + id + "\">");
                %>   
                <br>
                <input type="submit" value="Report" class="btn btn-danger" id="report-btn">
                   </div>
               </form>
             </div>
           </div>

         </div>
       
       <script>
           // Get the modal
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("modal-btn");

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks on the button, open the modal 
            btn.onclick = function() {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function() {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
       </script>

       
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
