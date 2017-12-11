package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bassyouni
 */
public class Router {

    public Router() {
    }
    
    public String showIncorrenctUsernameOrPassword()
    {
        return "<div class='incorrect-box'>\n" +
"            <p>Username Or Password are Incorrect ( Matsee3sh yad )</p>\n" +
"        </div>";
    }
    
    
    
    public String userFirstTime(ServletContext application , String sessionManager)
    {
        /*
            <div class='wrapper'>
                    <div class='container'>
                            <h1>Welcome</h1>

                            <form class='form' action='StoreTheSession' method='GET'>
                                    <input type='email' placeholder='Email' required='true'>
                                    <input type='password' name='password' placeholder='Password' required='true'>
                                    <button type='submit' id='login-button'>Login</button>
                            </form>
                    </div>
        
                    <div class="container">
                            <h3>Or</h3>

                            <form class='form' action='SignUp.jsp' method='GET'>
                                    <input type='submit' id='login-button' value='SignUp>
                            </form>
                    </div>

                    <ul class='bg-bubbles'>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                    </ul>
            </div>
        */
            //First time
                String result = "<div class=\"wrapper\">\n" +
"                    <div class=\"container\">\n" +
"                            <h1>Welcome</h1>\n" +
"\n" +
"                            <form class='form' action='StoreTheSession' method='GET'>\n" +
"                                    <input class='input' type='email' name='email' placeholder='Email' required='true'>\n" +
"                                    <input class='input' type='password' name='password' placeholder='Password' required='true'>\n" +
"                                    <input type='submit' id='login-button' value='Login'>" +
"                            </form>\n" +
"                    </div>\n" +
"\n" +
                        "<div class=\"container\">\n" +
"                            <h3>Or</h3>\n" +
"\n" +
"                            <form class='form' action='SignUp.jsp' method='GET'>\n" +
"                                    <input type='submit' id='login-button' value='SignUp'>\n" +
"                            </form>\n" +
"                    </div>"+
"                    <ul class='bg-bubbles'>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                            <li></li>\n" +
"                    </ul>\n" +
"            </div>";
             
                
                if (application.getAttribute(sessionManager) == null)
                {
                    HashMap<String, HttpSession> myHash = new HashMap<>();
                    application.setAttribute(sessionManager, myHash);
                }
          return result;
    }
    
    public String showLogut()
    {
        String result = "<form action=\"logout\"><br><input type=\"submit\" value=\"Logout\"></form>";
        return result;
    }
    
   
}
