<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Template</title>
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
                <sf:form class="form-horizontal" role="form" modelAttribute="hero" action="editHero" method="POST">
                    <div class="form-group">
                        <label for="Alias" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Alias:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <sf:input type="text" class="add-form form-control" id="Alias" path="alias" placeholder="Alias" name="alias"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="First Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            First Name:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <sf:input type="text" class="add-form form-control" path="firstName" placeholder="First Name" name="firstName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Last Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Last Name:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <sf:input type="text" class="add-form form-control" path="lastName" placeholder="Last Name" name="lastName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Existing Power(s):
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12 ">
                            <c:forEach var="power" items="${hasPowers}">
                                <div class="col-md-12">
                                    <label>${power.power}</label>
                                    <a path="power" name="power" href="${pageContext.request.contextPath}/edit/deletePower?powerId=${power.powerId}&heroId=${hero.heroId}"> 
                                        <span class="glyphicon glyphicon-remove"></span>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        </div>
                        <div class="row">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Assign New Power(s):
                            <a href="${pageContext.request.contextPath}/heroesPower/displayCreatePowerPage">
                                <br><br>
                            <button type='button' id='cancel-button' class='btn btn-large btn-primary'>
                                Create new power
                            </button>
                            </a>
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12 chkbox">
                            <c:forEach var="currentPower" items="${powers}">
                                <div class="col-md-12">
                                    <label>${currentPower.power}</label>
                                    <a path="power" name="power" href="${pageContext.request.contextPath}/edit/addPower?powerId=${currentPower.powerId}&heroId=${hero.heroId}"> 
                                        <span class="glyphicon glyphicon-plus"></span>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Organization(s):                            

                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="col-md-6">
                                    <input class="orgButton" type="radio" path="organization" name="organization" value="${inOrg.orgId}" checked><label>${inOrg.orgName}</label>
                                </div>
                            
                            <c:forEach var="org" items="${orgs}">
                                <div class="col-md-6">
                                    <input class="orgButton" type="radio" path="organization" name="organization" value="${org.orgId}"><label>${org.orgName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group" id="orgDate">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Date Joined:
                        </label>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                            <input type="date" class="add-form form-control" path="joinDate" name="joinDate"/>
                        </div>
                    </div>
                    <div class="form-group" id="leftDate">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Date Left:
                        </label>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                            <input type="date" class="add-form form-control" path="endDate" name="endDate"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Description" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Description:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <textarea class="form-control" path="description" name="description" row="5"></textarea>
                        </div>
                    </div>
                    <sf:hidden path="heroId"/>
                    <input type="text" style="display:none" name="oldOrg" path="oldOrg" value="${inOrg.orgId}"></input>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                            <button type="submit" class="btn btn-large btn-primary" id="create-button">
                                Edit Superhero/Supervillain
                            </button>
                        </div>    
                    </div>    
                </sf:form>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                        <a href="${pageContext.request.contextPath}/retrieval/displayAllHeroesPage">
                            <button type='button' id='cancel-button' class='btn btn-large btn-primary'>
                                Return to Heroes List
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