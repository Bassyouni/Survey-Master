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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/takeSurvey-style.css" rel="stylesheet">
        <%
           String id = request.getParameter("id");
           String surveyName = request.getParameter("surveyName");
       %>
        
       <title><%=surveyName%></title>
    </head>
    <body> 
        
       <%
           HashMap<String,String> selectAttributeMap = new HashMap();
           selectAttributeMap.put("id", id);
           Survey survey = Survey.getSurvey(Integer.valueOf(id), selectAttributeMap);
           survey.getAllQuestions();
           
           out.print("<form action=\"SaveSurveyAnswers\" id=\"" + id + "\">");
           out.print("<input type=\"hidden\" name=\"surveyId\" value=\"" + id + "\">");
           
           for(Question dummyQuestion : survey.questions)
           {
               out.print("<div>");
               out.print("<p><b>" + dummyQuestion.getQuestion() + "</b></p>");
               
               if (dummyQuestion.getType().equals(Question.MCQ))
               {
                    String givenAnswers = dummyQuestion.getGivenAnswers();
                    String[] answers = givenAnswers.split(",");
                    
                    for (String answer : answers)
                    {
                        out.print("<input type=\"radio\" name=\""+ dummyQuestion.getId() +"\" value=\"" + answer + "\" required> ");
                        out.print(answer + "<br>");
                    }
//                   <input type="radio" name="gender" value="male"> Male<br>
//                   <input type="radio" name="gender" value="female"> Female<br>
               }
               else if (dummyQuestion.getType().equals(Question.CHECKBOXES))
               {
                   String givenAnswers = dummyQuestion.getGivenAnswers();
                   String[] answers = givenAnswers.split(",");
                    
                   for (String answer : answers)
                   {
                       out.print("<input type=\"checkbox\" name=\""+ dummyQuestion.getId() +"\" value=\"" + answer + "\" checked> ");
                       out.print(answer + "<br>");
                   }
//                   <input type="checkbox" name="vehicle" value="Bike"> I have a bike<br>
//                   <input type="checkbox" name="vehicle" value="Car" checked> I have a car<br>
               }
               else if (dummyQuestion.getType().equals(Question.FREEANSWER))
               {
                   out.print("<input class='input' type=\"text\" name=\""+ dummyQuestion.getId() +"\" required>");
               }
               out.print("</div>");
           }
           
           out.print("<div>");
           out.print("<p><b>Do you want to show the creator your info?</b></p>");
           out.print("<input type=\"radio\" name=\"avail\" value=\"Anonymous\" required> Keep me Anonymous<br>");
           out.print("<input type=\"radio\" name=\"avail\" value=\"Show my Info\" required> Show my Info<br>");
           out.print("</div>");
           out.print("<br>");
           
           out.print("<input type=\"submit\" value=\"Submit Answers\">");
           
           out.print("</form>");
       %>
       
       <div id="report">
           <h2 id="heading">Danger Zone</h2> 
           <div id="box">
               <strong>Report This Survey</strong>
               <p>Once you report a survey, there is no going back. Please be certain.</p>
               <button type="button" class="btn-danger" id="modal-btn">
                Report This Survey
            </button>
           </div>
       </div>
       
                <!-- The Modal -->
         <div id="myModal" class="modal">

           <!-- Modal content -->
           <div class="modal-content">
             <div class="modal-header">
               <span class="close">&times;</span>
               <h2>Report</h2>
             </div>
             <div class="modal-body">
               <p>Please tell us why are you Reporting this Survey?</p>
               <form action="ReportSurvey" method="post">
                   <input class='input' type="text" name="comment" maxlength="120"  minlength="18" required="required">
                <% 
                    out.print("<input type=\"hidden\" name=\"surveyId\" value=\"" + id + "\">");
                %>   
                   <input type="submit" value="Report" class="btn-danger">
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
       
    </body>
</html>
