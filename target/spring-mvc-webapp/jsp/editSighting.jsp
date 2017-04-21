<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Editing Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">

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
        <div class="container">
            <div class="container">
                <sf:form class="form-horizontal" role="form" modelAttribute="sighting" action="editSighting" method="POST">
                    <div class="form-group">
                        <label for="Reported Time" class="col-md-4 col-sm-4 col-xs-12 control-label">
                            Reported Time:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <fmt:formatDate value="${sighting.sightingDateAsDate}" var="sightingDateString" pattern="yyyy-MM-dd'T'hh:mm"/>
                            <sf:input type="datetime-local" class="add-form form-control" path="sightingDate" value="${sightingDateString}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label">
                            Description:
                        </label>
                        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                            <sf:textarea class="form-control" path="description" name="description" row="5"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label">
                            Location:                            
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12 chkbox">
                                <div class="col-md-12">
                                    <input class="orgButton" type="radio" path="location" name="location" value="${currentLocation.locationId}" checked><label>${currentLocation.locationName}</label>
                                </div>
                            <c:forEach var="loc" items="${locations}">
                                <div class="col-md-12">
                                    <input class="orgButton" type="radio" path="location" name="location" value="${loc.locationId}"><label>${loc.locationName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Hero:                            
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12 chkbox">
                                <div class="col-md-12">
                                    <input class="orgButton" type="radio" path="hero" name="hero" value="${currentHero.heroId}" checked><label>${currentHero.alias}</label>
                                </div>
                            
                            <c:forEach var="superHero" items="${heroes}">
                                <div class="col-md-12">
                                    <input class="orgButton" type="radio" path="hero" name="hero" value="${superHero.heroId}"><label>${superHero.alias}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                                
                                
                                
                    <sf:hidden path="sightingId"/>
                    <input type="text" style="display:none" name="oldLocation" path="oldLocation" value="${currentLocation.locationId}"></input>
                    <input type="text" style="display:none" name="oldHero" path="oldHero" value="${currentHero.heroId}"></input>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                            <button type="submit" class="btn btn-large btn-primary" id="create-button">
                                Edit Sighting
                            </button>
                        </div>    
                    </div>    
                </sf:form>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                        <a href="${pageContext.request.contextPath}/sighting/displayAllSightingsPage">
                            <button type='button' id='cancel-button' class='btn btn-large btn-primary'>
                                Return to Sighting List
                            </button>
                        </a>
                    </div>    
                </div>    
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>