/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xml.ws.security.trust.WSTrustConstants;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // necssary for databas
            Class.forName("com.mysql.jdbc.Driver"); 
            
            //Form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String userPassword = request.getParameter("password");
           
            //database variables
            String url = "jdbc:mysql://localhost:3306/survey_db";
            String user = "root";
            String password = "root";
            Connection Con = DriverManager.getConnection(url, user, password);
            
            
            //Statment to insert user
            String insertQuery = "INSERT INTO `survey_db`.`users` (`is_administrator`, `name`, `password`, `email`, `is_suspended`) VALUES ('0', '" + name + "', '" + userPassword + "', '" + email + "', '0');";
            Statement Stmt = Con.createStatement();
            int Rows = Stmt.executeUpdate(insertQuery);
            System.out.println("Rows Affected: "+Rows);
            
            // statment to get user autoincrmented id to save in session
            String selectQuery = "SELECT id FROM survey_db.users where name = '"+ name +"' and password = '"+ userPassword +"' and email = '"+ email +"'";
            Stmt = Con.createStatement();
            
            ResultSet RS = Stmt.executeQuery(selectQuery);
            
            if (RS != null)
            {
                 String id = "";
                 while (RS.next()) 
                {                    
                    id = RS.getString("id");
                }
                 
                
            HttpSession session = request.getSession(true);

            session.setAttribute("name", name);
            session.setAttribute("id", id);
            session.setMaxInactiveInterval(60 * 60);
            
            System.out.println(session.getAttribute("name") + "    " + session.getAttribute("id"));
           
            
            //add session to sessionManager
            String SessionManager = "SessionManager";
            HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>)request.getServletContext().getAttribute(SessionManager);
            sessionMangerHash.put(session.getId(), session);
            
            //Add cookie
            Cookie cookie = new Cookie ("MyCurrentSession",session.getId());
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
                
            }
            

            response.sendRedirect("Login.jsp");
            
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
