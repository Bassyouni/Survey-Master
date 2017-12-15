<%-- 
    Document   : AddSurvey
    Created on : Dec 15, 2017, 10:26:55 PM
    Author     : cdc
--%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Statistics</title>
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- Custom styles for this template -->
        <link href="css/clean-blog.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
          <div class="container">
              <a class="navbar-brand" href="Home.jsp">Survey Master</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              Menu
              <i class="fa fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a class="nav-link" href="Home.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ChangePassword.jsp">Change Password</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="AddSurvey.jsp">Add Survey</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="logout">Log Out</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <!-- Page Header -->
        <header class="masthead" style="background-image: url('img/statistics.jpg')">
          <div class="overlay"></div>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                  <h1>Add Survey</h1>
                  <span class="subheading">Add your Survey here</span>
                </div>
              </div>
            </div>
          </div>
        </header>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
                <form name="sentMessage" id="contactForm" action="AddSurvey" method="Post">
                    <div class="control-group">
                      <div class="form-group floating-label-form-group controls">
                        <label>Survey Name</label>
                        <input name="survey-name" type="text" class="form-control" placeholder="Add survey name" id="survey-name" required data-validation-required-message="Please enter your survey name." minlength="8">
                      </div>
                    </div>
                    <div class="question-container">
                        
                    </div>
                    
                    <br>
                    <div class="form-group">
                      <button type="submit" class="btn btn-primary" id="sendMessageButton">Save Survey</button>
                      <button type="button" class="btn btn-success" id="add-radio-button">add radio</button>
                      <button type="button" class="btn btn-success" id="add-check-button">add check</button>
                      <button type="button" class="btn btn-success" id="add-text-button">add text</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
                
        <!-- Footer -->
        <footer>
          <div class="container">
            <div class="row">
              <div class="col-lg-8 col-md-10 mx-auto">
                <ul class="list-inline text-center">
                  <li class="list-inline-item">
                    <a href="#">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </li>
                </ul>
                <p class="copyright text-muted">Copyright &copy; SurveyMaster 2017</p>
              </div>
            </div>
          </div>
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Custom scripts for this template -->
        <script src="js/clean-blog.min.js"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="js/dynamic-form.js" type="text/javascript"></script>
    </body>
</html>
