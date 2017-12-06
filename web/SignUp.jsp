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
            
        </style>
        
        <title>Sign Up</title>
    </head>
    <body>
        
        <div id="wrapper">
        <form action="SaveUser" method="post">
            <div class="form-element">
             
            <label for="name">Name</label>
            <input type="text" required="true" name="name" placeholder="Ex. Ahmed Abdel latif">
            
            </div>
            
            <div class="form-element">
                
            <label for="email">Email</label>
            <input type="email" required="true" name="email"  placeholder="Ex. yourname@gmail.com">
           
            </div>
            
            <div class="form-element">
            
            <label for="password">Password</label>
            <input type="password" required="true" name="password" minlength="8">
            
            </div>
            
            </div>
            
            <div class="form-element">
            
                <input type="submit" value="Sign Up" id="submitBtn">
            
            </div>
            
            
        </form>
        </div>

        
    </body>
</html>
