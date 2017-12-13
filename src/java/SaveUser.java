/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.User;
import com.sun.xml.ws.security.trust.WSTrustConstants;
import database.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Bassyouni
 */
@WebServlet(urlPatterns = {"/SaveUser"})
public class SaveUser extends HttpServlet {

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
    private static final String userTable = "users";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            //Form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String userPassword = request.getParameter("password");

            //check if this email was used or not
            HashMap<String, String> attrHash = new HashMap<>();
            attrHash.put("email", email);
            ResultSet checkUniqueEmailResultSet = databaseConnection.select(userTable, attrHash);
            
            if (checkUniqueEmailResultSet != null) 
            {
                if (checkUniqueEmailResultSet.next()) 
                {
                    //email not unique and exists in the db!
                    request.setAttribute("emailExists", "");
                    RequestDispatcher goToSignUp = request.getRequestDispatcher("SignUp.jsp");
                    goToSignUp.forward(request, response);
                } else 
                {
                    //email is unique and doesnt exist in db!

                    //Statment to insert user
                    User createdUser = new User("0", name, userPassword, email, "0");

                    databaseConnection.insertInto(userTable, createdUser.getAttributes());

                    // statment to get user autoincrmented id to save in session
                    ResultSet RS = databaseConnection.select(userTable, createdUser.getAttributes());

                    if (RS != null) 
                    {
                        String id = "";
                        Boolean isAdmin = null;
                        while (RS.next()) 
                        {
                            id = RS.getString("id");
                            isAdmin = RS.getBoolean("is_administrator");
                        }

                        HttpSession session = request.getSession(true);

                        session.setAttribute("name", name);
                        session.setAttribute("id", id);
                        session.setAttribute("isAdmin", isAdmin);
                        session.setMaxInactiveInterval(60 * 60);

                        System.out.println(session.getAttribute("name") + "    " + session.getAttribute("id"));

                        //add session to sessionManager
                        String SessionManager = "SessionManager";
                        HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>) request.getServletContext().getAttribute(SessionManager);
                        sessionMangerHash.put(session.getId(), session);

                        //Add cookie
                        Cookie cookie = new Cookie("MyCurrentSession", session.getId());
                        cookie.setMaxAge(60 * 60);
                        response.addCookie(cookie);

                    }

                    response.sendRedirect("Login.jsp");
                }
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SaveUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
