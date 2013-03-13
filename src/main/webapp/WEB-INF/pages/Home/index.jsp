<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bootstrap 101 Template</title>
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.mustache.js"></script>
        <script src="js/aerogear.min.js"></script>

        <script src="js/app.js"></script>
    </head>
    <body>
        <div class="bs-docs-example">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" href="#">Aerogear Manager</a>
                        <nav class="nav-collapse collapse navbar-responsive-collapse">
                            <ul class="nav">
                                <li class="active"><a href="#">Dashboard</a></li>
                                <li><a href="#">Stats</a></li>
                                <li><a href="#" id ="pipes">Pipes</a></li>
                                <li><a href="#">Push</a></li>
                                <li><a href="#">Security</a></li>
                            </ul>

                            <ul class="nav pull-right">
                                <li class="divider-vertical"></li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Download<b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Android Template</a></li>
                                        <li><a href="#">Javascript Template</a></li>
                                        <li><a href="#">iOS Template</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </nav><!-- /.nav-collapse -->
                    </div>
                </div><!-- /navbar-inner -->
            </div><!-- /navbar -->
        </div>
        <div id="app"></div>

    </body>
</html>