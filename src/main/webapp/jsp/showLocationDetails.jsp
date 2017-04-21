<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization Detail</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <style>
            a{
                color:white;
            }
        </style>
    </head>
    <body>
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
        <!-- Placed at the end of the document so the pages load faster -->
        <h1 class="text-center">Classified</h1>
        <div class="row text-center">
            <label>Name: </label>
            <c:out value="${location.locationName}"/>
        </div>
        <div class="row text-center">
            <label>Description: </label>
            <c:out value="${location.description}"/>
        </div>
        <div class="row text-center">
            <label>Latitude: </label>
            <c:out value="${location.latitude}"/>
        </div>
        <div class="row text-center">
            <label>Longitude: </label>
            <c:out value="${location.longitude}"/>
        </div>
        <div class="row text-center">
            <label>Address: </label>
            <c:out value="${street.address}"/>
        </div>
        <div class="row text-center">
            <label>City: </label>
            <c:out value="${street.city}"/>
        </div>
        <div class="row text-center">
            <label>State/Province: </label>
            <c:out value="${street.state}"/>
        </div>
        <div class="row text-center">
            <label>Country: </label>
            <c:out value="${street.country}"/>
        </div>
        </div>
        <div class="row text-center">
            <label>Zip: </label>
            <c:out value="${street.zip}"/>
        </div>
        </div>
        <div class="row text-center">
            <label>World: </label>
            <c:out value="${street.world}"/>
        </div>

        <div class="row text-center">
            <label>Sighting reported: </label>
            <c:forEach var="sighting" items="${sightings}">
                <a href="${pageContext.request.contextPath}/retrieval/displaySightingDetails?sightingId=${sighting.sightingId}">
                <c:out value="${sighting.sightingDate}"/><br>
                </a>
            </c:forEach>
        </div>
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/retrieval/displayAllLocationsPage">
                <button type='button' id='cancel-button' class='btn btn-default'>
                    Return to Location List
                </button>
            </a>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>