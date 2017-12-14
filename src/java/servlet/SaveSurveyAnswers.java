/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.Answer;
import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Bassyouni
 */
@WebServlet(name = "SaveSurveyAnswers", urlPatterns = {"/SaveSurveyAnswers"})
public class SaveSurveyAnswers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    private static final String answerTableName = "answer";
    private static final String surveyTableName = "surveys";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            HttpSession mySession = request.getSession();
            String surveyId = request.getParameter("surveyId");
            String userId = mySession.getAttribute("id").toString();
            
            Map<String, String[]> parameterMap = request.getParameterMap();
            
            ArrayList<Answer> answers = new ArrayList<>();
            
            // Getting all answers and putting them to an array
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
            {
                
                if (entry.getKey().equals("surveyId")) 
                {
                    continue;
                }
                
                Answer answer = new Answer();
                answer.setSurveyId(surveyId);
                answer.setQuestionId(entry.getKey());
                
                if(request.getParameter("avail").equals("Anonymous"))
                {
                    answer.setUserId(null); 
                }
                else
                {
                    answer.setUserId(userId);
                }
               
                
                String questionAnswer = "";
                for(String value : entry.getValue())
                {
                    questionAnswer += value;
                    questionAnswer += ",";
                }
                questionAnswer = questionAnswer.substring(0, questionAnswer.length() - 1);
                
                answer.setAnswer(questionAnswer);
                
                answers.add(answer);    
            }
            
            // Save the answers to the database
            answers.forEach((answer) -> {
                databaseConnection.insertInto(answerTableName, answer.getAttributes());
            });
            
            //increment Survey counter by 1 for statistics 
            
            databaseConnection.incrementSurveyCounterByOne(surveyId);
            
            response.sendRedirect("Home.jsp");
            
            
            

        }
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

}
