/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.Survey;
import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cdc
 */
@WebServlet(name = "SuspendSurvey", urlPatterns = {"/SuspendSurvey"})
public class SuspendSurvey extends HttpServlet {

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
    private static final String surveyTableName = "surveys";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("atleast this ran!!");
            
            response.sendRedirect("Profile.jsp");
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
        response.setContentType("text");
        HashMap<String, String> selectAttributeMap = new HashMap<>();
        int surveyId = Integer.parseInt(request.getParameter("Id"));
        selectAttributeMap.put("id", String.valueOf(surveyId));
        Survey currentSurvey = Survey.getSurvey(surveyId, selectAttributeMap);
        
        HashMap<String, String> updateAttributeMap = new HashMap<>();
        updateAttributeMap = new HashMap<>();
        System.out.println(currentSurvey.isSuspended());
        if(currentSurvey.isSuspended())
        {
            updateAttributeMap.put("isSuspended", "0");
            int rows = databaseConnection.update(surveyTableName, updateAttributeMap, surveyId);
        }
        else{
            updateAttributeMap.put("isSuspended", "1");
            int rows = databaseConnection.update(surveyTableName, updateAttributeMap, surveyId);
        }
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
