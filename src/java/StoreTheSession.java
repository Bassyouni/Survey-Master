/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/StoreTheSession"})
public class StoreTheSession extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver"); 

            
            String SessionManager = "SessionManager";
            
            String email = request.getParameter("email");
            String userPassword = request.getParameter("password");
           
            String url = "jdbc:mysql://localhost:3306/survey_db";
            String user = "root";
            String password = "24972052";
            
            Connection Con = DriverManager.getConnection(url, user, password);
            
            //Statment 
            String selectQuery = "SELECT * FROM survey_db.users where email = '" + email + "' and password = '"+ userPassword +"' ;";
            Statement Stmt = Con.createStatement();
            
            ResultSet RS = Stmt.executeQuery(selectQuery);
            
            if (RS != null)
            {
                 String id = "";
                 String name = "";
                 
                 boolean isFound = false;
                 while (RS.next()) 
                {                    
                    id = RS.getString("id");
                    name = RS.getString("name");
                    isFound = true;
                }
                 
                 if(isFound)
                 {
                     //Making a session and saving in it name and id of the user
                    HttpSession session = request.getSession(true);
                    session.setAttribute("name", name);
                    session.setAttribute("id", id);
                    session.setMaxInactiveInterval(60 * 60);
           
                    //add session to sessionManager
                    HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>)request.getServletContext().getAttribute(SessionManager);
                    sessionMangerHash.put(session.getId(), session);
            
                    //Add cookie
                    Cookie cookie = new Cookie ("MyCurrentSession",session.getId());
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);
                    response.sendRedirect("Home.jsp");  
                 }
                 else
                 {
                     //Add incorect username or password cookie
                    Cookie cookie = new Cookie ("userNotFound","true");
                    cookie.setMaxAge(60 * 60);
                    response.addCookie(cookie);
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
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            ex.getMessage();
            ex.getErrorCode();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreTheSession.class.getName()).log(Level.SEVERE, null, ex);
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
