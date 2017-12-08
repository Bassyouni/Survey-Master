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
        return "<p><b><font color=\"red\">Incorrect username or password</font></b></p>";
    }
    
    
    
    public String userFirstTime(ServletContext application , String sessionManager)
    {

            //First time
                String result = "<div id=\"wrapper\">"
                        + "<form action=\"StoreTheSession\" method=\"get\">\n" +
              "\n" +
"            <table cellspacing=\"5\" border=\"0\">\n" +
"                <div class=\"form-element\"><tr>\n" +
"                    <td align=\"right\">Email address:</td>\n" +
"                    <td><input type=\"email\" name=\"email\" required=\"true\" ></td>\n" +
"                    \n" +
"                </tr></div>\n" +
"                <div class=\"form-element\"><tr>\n" +
"                    <td align=\"right\">Password</td>\n" +
"                    <td><input type=\"password\" name=\"password\" required=\"true\" minlength=\"8\" ></td>\n" +
"                </tr></div>\n" +
"                <div class=\"form-element\"><tr>\n" +
"                    <td></td>\n" +
"                    <td><br><input type=\"submit\" value=\"Sign in\"></td>\n" +
"                </tr></div>\n" +
"\n" +
"            </table>\n" +
"\n" +
"        </form>  <form align= \"center\" action=\"SignUp.jsp\">\n" +
"        <p> OR </p><br>    <input type=\"submit\" value=\"Sign up\">\n" +
"        </form>        </div>\n" +
"";
             
                
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
