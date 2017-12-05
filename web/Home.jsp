<%-- 
    Document   : Home
    Created on : Dec 5, 2017, 3:48:24 AM
    Author     : Bassyouni
--%>
<%@page import="Model.Router"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <%
            Router router = new Router();
                String name = session.getAttribute("name").toString();

                String id = session.getAttribute("id").toString();

                out.print(name + " " + id );
                
                out.print(router.showLogut());
                
                

                   
        
        
        %>
    </body>
</html>
