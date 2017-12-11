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
        <link href="css/form-style.css" rel="stylesheet">
        <title>Sign Up</title>
    </head>
    <body>
            <%
                                        if(request.getAttribute("emailExists") != null)
                                        {
                                            String output = "<div class='incorrect-box'>\n" +
                        "            <p>There is a profile with the same email, login with it </p>\n" +
                        "        </div>";
                                            out.print(output);
                                            request.removeAttribute("emailExists");
                                        }
                                    %>
            <div class='wrapper'>
                    <div class='container'>
                            <h1>Welcome</h1>

                            <form class='form' action='SaveUser' method='GET'>
                                    <input class='input' type='text' name='name' placeholder='Name' required='true'>
                                    <input class='input' type='email' name='email' placeholder='Email' required='true'>
                                    <input class='input' type='password' name='password' placeholder='Password' required='true'>
                                    <Button type='submit' id='login-button'>SignUp</button
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
            
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script  src="js/form-script.js"></script>
    </body>
</html>
