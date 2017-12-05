/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/logout"})
public class logout extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

             String SessionManager = "SessionManager";
            String cookieName = "MyCurrentSession";
            Cookie cookies [] = request.getCookies ();
            Cookie myCookie = null;           
            
            if (cookies != null)
            {
                for (int i = 0; i < cookies.length; i++) 
                {
                    if (cookies [i].getName().equals (cookieName))
                    {
                        myCookie = cookies[i];
                        break;
                    }
                }
            }
            
            if(myCookie != null)
            {
                try { 
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                String sessionID = myCookie.getValue();
                
                String url = "jdbc:mysql://localhost:3306/session_manager";
                String user = "root";
                String password = "root";
                String query = "DELETE FROM `session_manager`.`user` WHERE `session_ID`='" + sessionID + "';";
                        
                HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>)request.getServletContext().getAttribute(SessionManager);
                sessionMangerHash.remove(sessionID);
                
                myCookie.setValue(null);
                myCookie.setMaxAge(0);
                response.addCookie(myCookie);
                
                Connection Con = DriverManager.getConnection(url, user, password);
            
                //Statment 
                Statement Stmt = Con.createStatement();
                int Rows = Stmt.executeUpdate(query);
                System.out.println("Rows Affected From : "+Rows);
            }
            else
            {
                System.out.println("logout.processRequest() ,, something is wrong no cookie found");
            }
            response.sendRedirect("Intro.jsp");
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
            Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(logout.class.getName()).log(Level.SEVERE, null, ex);
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
