<%-- 
    Document   : SignUp
    Created on : Dec 4, 2017, 11:52:32 PM
    Author     : Bassyouni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <body>
        
        <form action="SaveUser" method="post">
            
            Name <input type="text" required="true" name="name"> <br>
            Email <input type="email" required="true" name="email"> <br>
            Password <input type="password" required="true" name="password" minlength="8"> <br>
            <input type="submit">
            
        </form>
        
        
    </body>
</html>
