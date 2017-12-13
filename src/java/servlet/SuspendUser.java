/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.Survey;
import Model.User;
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
@WebServlet(name = "SuspendUser", urlPatterns = {"/SuspendUser"})
public class SuspendUser extends HttpServlet {

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
    private static final String userTableName = "users";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("atleast this ran!!");
            
            response.sendRedirect("AdminUserFeed.jsp");
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
        int userId = Integer.parseInt(request.getParameter("id"));
        selectAttributeMap.put("id", String.valueOf(userId));
        User currentUser = User.selectUser(String.valueOf(userId));
        
        HashMap<String, String> updateAttributeMap = new HashMap<>();
        System.out.println(currentUser.getIsSuspended());
        if(currentUser.getIsSuspended().equals("1"))
        {
            updateAttributeMap.put("is_suspended", "0");
            int rows = databaseConnection.update(userTableName, updateAttributeMap, userId);
        }
        else{
            updateAttributeMap.put("is_suspended", "1");
            int rows = databaseConnection.update(userTableName, updateAttributeMap, userId);
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
