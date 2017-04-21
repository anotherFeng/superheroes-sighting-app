<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HOME</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <style>


            .carousel {
                margin-bottom: 20px;
            }

            .carousel .container {
                position: relative;
                z-index: 9;
            }

            .carousel-control {
                height: 80px;
                margin-top: 0;
                font-size: 120px;
                text-shadow: 0 1px 1px rgba(0,0,0,.4);
                background-color: transparent;
                border: 0;
                z-index: 10;
            }

            .carousel .item {
                min-height:500px;
                max-height:500px;
                height: 500px;
            }
            .carousel img {
                position: absolute;
                top: 0;
                left: 0;
                min-width: 100%;
                height: 500px;
            }

            .carousel-caption {
                background-color: transparent;
                position: static;
                max-width: 550px;
                padding: 0 20px;
                margin-top: 200px;
            }
            .carousel-caption h1,
            .carousel-caption .lead {
                margin: 0;
                line-height: 1.25;
                color: #fff;
                text-shadow: 0 1px 1px rgba(0,0,0,.4);
            }
            .carousel-caption .btn {
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="wrapper">
        <div class="container-fluid ">
            <div class="row">
                <nav class="navbar navbar-inverse">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNav">
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="active navbar-brand glyphicon glyphicon-home" href="${pageContext.request.contextPath}/"></a>
                        </div>
                        <div class="collapse navbar-collapse" id="mainNav">
                            <ul class="nav navbar-nav navbar-right">
                                <li role="presentation"><a href="${pageContext.request.contextPath}/sighting/displayReportSightingPage">Report a sighting</a></li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Heroes<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateHeroPage">Add Hero</a></li>
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllHeroesPage">Show All Heroes</a></li>
                                    </ul>
                                </li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Powers<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreatePowerPage">Add Power</a></li>
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllPowersPage">Show All Powers</a></li>
                                    </ul>
                                </li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Organizations<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateOrgPage">Add Organization</a></li>
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllOrganizationsPage">Show All Organizations</a></li>
                                    </ul>
                                </li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Locations<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateLocationPage">Add Location</a></li>
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllLocationsPage">Show All Locations</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">

                <c:forEach var="sighting" items="${sightings}">
                    <div class="item">
                        <div class="container">
                            <div class="carousel-caption">
                                <h1>${sighting.hero.alias}</h1>
                                <p class="lead">${sighting.description}</p>
                                <p>Sighted Time: ${sighting.sightingDate}</p>
                                <a class="btn btn-large btn-primary" href="${pageContext.request.contextPath}/retrieval/displaySightingDetails?sightingId=${sighting.sightingId}">More Detail</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!--      <div class="carousel-inner">
                -->        <div class="item active">
                    <img src="http://wallpapercave.com/wp/dbxe6nd.jpg">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Wolverine</h1> 
                            <p class="lead">......</p>  
                            <a class="btn btn-large btn-primary" href="#">More Detail</a> 
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Ironman</h1>
                            <p class="lead">That was money well spent.</p>
                            <a class="btn btn-large btn-primary" href="#">More Detail</a>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="https://images3.alphacoders.com/723/72397.jpg" alt="">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1>Batman</h1>
                            <p class="lead">I'm Batman!</p>
                            <a class="btn btn-large btn-primary" href="#">More Detail</a>
                        </div>
                    </div>
                </div><!--
              </div>-->
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
            </div>
            
        </div>
              <div class="push"></div>
        </div>
        <div class="copyright footer">
            <div class="container">
                <div class="col-md-6">
                    <p>© 2017 - No Rights Reserved - Feng Chen</p>
                </div>
            </div>
        </div>   
        <!-- /.carousel -->         
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

