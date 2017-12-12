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
                   out.print("<input type=\"text\" name=\""+ dummyQuestion.getId() +"\" required>");
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


    </body>
</html>
