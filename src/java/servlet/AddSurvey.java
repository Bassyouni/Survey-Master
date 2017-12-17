/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.Question;
import Model.Survey;
import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cdc
 */
@WebServlet(name = "AddSurvey", urlPatterns = {"/AddSurvey"})
public class AddSurvey extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String surveyTableName = "surveys";
    private static final String questionsTableName = "question";
    
    String CurrentUserId;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Home.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession currentUserSession = request.getSession();
        CurrentUserId = currentUserSession.getAttribute("id").toString();
        parseForm(request, response);
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void parseForm(HttpServletRequest request, HttpServletResponse response){
        int surveyId = addSurveyName(request, response);
        String checkPrefix = "check";
        String textPrefix = "text";
        String radioPrefix = "radio";
        
        parseQuestions(surveyId, checkPrefix , request, response);
        parseQuestions(surveyId, radioPrefix , request, response);
        parseQuestions(surveyId, textPrefix , request, response);
    }
    
    private int addSurveyName(HttpServletRequest request, HttpServletResponse response){
        String surveyName = request.getParameter("survey-name").toString();
        Survey addedSurvey = new Survey();
        addedSurvey.setName(surveyName);
        addedSurvey.setOwnerId(Integer.parseInt(CurrentUserId));
        
        HashMap<String, String> input = new HashMap<String, String>();
        input.put("name", surveyName);
        input.put("owner", CurrentUserId);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        int inserted = databaseConnection.insertInto(surveyTableName, input);
        
        addedSurvey = Survey.getSurvey(input);
        if(inserted == 1)
            return addedSurvey.getId() ;
        else
            return addedSurvey.getId();
    }

    private void parseQuestions(int surveyId, String questionNamePrefix, HttpServletRequest request, HttpServletResponse response){
        //questionParamterName:    
        //text-question-1, check-question-1, radio-question-2
        //answers: check-question-1-answer-1, radio-question-2-answer-2
        String questionName;
        String answerName;//String.format(questionNamePrefix + "-question-%d", questionCounter)
        int questionCounter = 1;
        int answerCounter = 1;
        String question;
        String answer;
        boolean hasNextQuestion = true;
        boolean hasNextAnswer;
        ArrayList<Question> questionsList = new ArrayList<Question>();
        
        while(hasNextQuestion){
            hasNextAnswer = true;
            answer = "";
            questionName = String.format(questionNamePrefix + "-question-%d", questionCounter);
            question = request.getParameter(questionName);
            if(question == null)
                hasNextQuestion = false;
            else
            {
                questionCounter++;
                
                while(hasNextAnswer){
                    answerName = String.format(questionName + "-answer-%d", answerCounter);
                    if(request.getParameter(answerName) == null){
                        answerCounter = 1;
                        hasNextAnswer = false;
                    }
                    else{
                        if(answerCounter == 1)
                            answer += request.getParameter(answerName);
                        else
                            answer += "," + request.getParameter(answerName);
                        
                        answerCounter++;
                    }
                }
                Question currentQuestion = new Question();
                currentQuestion.setQuestion(question);
                currentQuestion.setGivenAnswers(answer);
                currentQuestion.setSurveyiD(surveyId);
                if(questionNamePrefix.equals("text")){
                    currentQuestion.setType(Question.FREEANSWER);
                }
                else if(questionNamePrefix.equals("radio")){
                    currentQuestion.setType(Question.MCQ);
                }
                else if(questionNamePrefix.equals("check")){
                    currentQuestion.setType(Question.CHECKBOXES);
                }
                questionsList.add(currentQuestion);
                
            }
        }
        //inserting the question to database
            DatabaseConnection databaseConnection = new DatabaseConnection();
            System.out.println(questionsList);
            for(int i = 0; i < questionsList.size(); i++){
                databaseConnection.insertInto(questionsTableName, questionsList.get(i).getAttributes());
            }
        
        
    }
}
