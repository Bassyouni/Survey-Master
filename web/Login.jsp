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
        <title>FCI Manager</title>
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
                        out.print(router.showUserInfo(userSession, sessionMangerHash.size()));
                        out.print(router.showLogut());            
                    }

                }
            }
            
        %>
    </body>
</html>
