<%-- 
    Document   : Intro
    Created on : Nov 6, 2017, 7:23:38 PM
    Author     : Bassyouni
--%>

<%@page import="Model.Router"%>
<%@page import="mailapp.SendMail" %>
<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="Intro.jsp"></a>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/form-style.css" rel="stylesheet">
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        
        <title>Login</title>
    </head>
    <body>
        
        <%
            if(false)
            {
                String user = "surveymaster.fci@gmail.com";
                String pass = "poiuytrewq0987654321";
                SendMail.send("yosef_sanad@hotmail.com", "hi", "ana aho yad ya bassiony ya nems");
            }
            
            Router router = new Router();
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
                        out.print(router.showIncorrenctUsernameOrPassword());
                        myCookie = cookies[i];
                        break;
                    }
                    if (cookies[i].getName().equals("userNotFound"))
                    {
                        out.print(router.showIncorrenctUsernameOrPassword());
                        Cookie cookie = cookies[i];
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        break;
                    }
                }
            }

            //First time
            if (myCookie == null)
            {
                out.print(router.userFirstTime(application, SessionManager));
            }
            else // Not First time
            {
                //Does the sessionManger object exists?
                if (application.getAttribute(SessionManager) == null)
                {
                    myCookie.setValue(null);
                    myCookie.setMaxAge(0);
                    response.addCookie(myCookie);
                    out.print(router.userFirstTime(application, SessionManager));
                }
                else
                {
                    String sessionID = myCookie.getValue();
                    HashMap<String, HttpSession> sessionMangerHash = (HashMap<String, HttpSession>)application.getAttribute(SessionManager);
                    HttpSession userSession =  sessionMangerHash.get(sessionID);

                    //is there a session with sessionID retrived from the cookie
                    if (userSession == null)
                    {
                        myCookie.setValue(null);
                        myCookie.setMaxAge(0);
                        response.addCookie(myCookie);
                        out.print(router.userFirstTime(application, SessionManager));
                    }
                    else
                    {
                        HttpSession s = request.getSession(true);
                        s.setAttribute("name", userSession.getAttribute("name").toString());
                        s.setAttribute("id", userSession.getAttribute("id").toString());
                        Boolean isAdmin = (Boolean) userSession.getAttribute("isAdmin");
                        if(isAdmin != null)
                        {
                            if(isAdmin.booleanValue())
                            {
                                response.sendRedirect("AdminSurveyFeed.jsp");
                            }
                            else{
                                response.sendRedirect("Home.jsp");
                            }
                        }
                        else{
                            response.sendRedirect("Home.jsp");
                        }
                        
                       
                    }
                }
            }
            
        %>
        
        <script  src="js/form-script.js"></script>
    </body>
</html>
