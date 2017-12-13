/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Model.User;
import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ChangePassword", urlPatterns = {"/ChangePassword"})
public class ChangePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private User currentUser;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
            HttpSession currentUserSession = request.getSession();
            if(changePassword(request, response)){
                currentUserSession.setAttribute("validPassword", true);
                if(currentUser.getIsAdministrator().equals("1"))
                {
                    response.sendRedirect("AdminUserFeed.jsp");
                }
                else
                    response.sendRedirect("Profile.jsp");
            }
            else{
                currentUserSession.setAttribute("validPassword", false);
                if(currentUser.getIsAdministrator().equals("1"))
                {
                    response.sendRedirect("AdminChangePassword.jsp?targetUserId=" + currentUserSession.getAttribute("targetUserId").toString());
                }
                else
                    response.sendRedirect("ChangePassword.jsp");
            }
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
    
    private boolean changePassword(HttpServletRequest request, HttpServletResponse response){
        boolean changed = false;
        String userTableName = "users";
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmationPassword = request.getParameter("confirmationPassword");
        
        HttpSession currentUserSession = request.getSession();
        String currentUserId = currentUserSession.getAttribute("id").toString();
        currentUser = User.selectUser(currentUserId);
        if(currentUser.getIsAdministrator().equals("1")  && newPassword.equals(confirmationPassword)){
             HashMap<String, String> input = new HashMap<>();
             User targetUser = User.selectUser(currentUserSession.getAttribute("targetUserId").toString());
             if(targetUser.getPassword().equals(oldPassword)){
                input.put("password", newPassword);
                DatabaseConnection databaseConnection = new DatabaseConnection();
                databaseConnection.update(userTableName, input, Integer.parseInt(targetUser.getId()));
                changed = true;
             }
        }
        else if(oldPassword.equals(currentUser.getPassword())  && newPassword.equals(confirmationPassword))
        {
            HashMap<String, String> input = new HashMap<>();
            input.put("password", newPassword);
            DatabaseConnection databaseConnection = new DatabaseConnection();
            databaseConnection.update(userTableName, input, Integer.parseInt(currentUserId));
            changed = true;
        }
        
        return changed;
    }

}
