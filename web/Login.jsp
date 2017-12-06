<%-- 
    Document   : Intro
    Created on : Nov 6, 2017, 7:23:38 PM
    Author     : Bassyouni
--%>

<%@page import="Model.Router"%>

<%@page import="java.lang.String"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="Intro.jsp"></a>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        
        <style type="text/css">
            
            body {
               font-family: helvetica, sans-serif; 
               font-size: 130%;
            }
            
            input {
                padding: 5px 5px 12px 5px; 
                font-size: 25px;
                border-radius: 5px;
                border: 1px solid grey;
                width: 320px;
            }
            
            label {
                position: relative;
                top: 12px;
                width: 200px;
                float: left;
            }
            
            #wrapper {
                width: 550px;
                margin: 0 auto;
                 
            }
            
            .form-element {
                margin-bottom: 10px;
            }
            
            #submitBtn
            {
                width: 130px;
                margin-left: 200px;
            }
            
            p {
               margin: 0 auto;
               position: relative;
            }
            
        </style>
        
        <title>Survey</title>
    </head>
    <body>
        
        <%
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
                        // why is the session gets deleted when closing the browser?
                        // this code below is a work around solution and most importantly it works
                        HttpSession s = request.getSession(true);
                        s.setAttribute("name", userSession.getAttribute("name").toString());
                        s.setAttribute("id", userSession.getAttribute("id").toString());
                       response.sendRedirect("Home.jsp");
                    }
                }
            }
            
        %>
    </body>
</html>
