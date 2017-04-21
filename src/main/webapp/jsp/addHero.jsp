<%-- 
    Document   : addHero
    Created on : Mar 26, 2017, 7:14:56 PM
    Author     : feng
--%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Heroes</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
         <link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet">
         <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
    </head>
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
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateHeroPage">> Add Hero</a></li>
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
                <form class="form-horizontal" role="form" modelAttribute="hero" action="createHero" method="POST">
                    <div class="form-group">
                        <label for="Alias" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Alias:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <input type="text" class="add-form form-control" path="alias" placeholder="Alias" name="alias" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="First Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            First Name:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <input type="text" class="add-form form-control" path="firstName" placeholder="First Name" name="firstName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Last Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Last Name:
                        </label>
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <input type="text" class="add-form form-control" path="lastName" placeholder="Last Name" name="lastName"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Super Power(s):
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
                                    <input type="checkbox" path="power" name="power" value="${currentPower.powerId}"><label>${currentPower.power}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Organization(s):
                            <a href="${pageContext.request.contextPath}/heroesPower/displayCreateOrgPage">
                                <br><br>
                            <button type='button' id='cancel-button' class='btn btn-large btn-primary'>
                                Add Organization
                            </button>
                            </a>
                        </label>
                        <div class="chkbox col-md-4 col-sm-6 col-xs-12">
                            <c:forEach var="org" items="${orgs}">
                                <div class="col-md-12">
                                    <input class="orgButton" type="radio" path="organization" name="organization" value="${org.orgId}" ${org.orgName == 'NONE' ? 'checked' : ''}><label>${org.orgName}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group" id="orgDate" style="display: none">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-12 control-label control-label">
                            Date Joined:
                        </label>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                            <input type="date" class="add-form form-control" path="joinDate" name="joinDate"/>
                        </div>
                    </div>
                    <div class="form-group" id="leftDate" style="display: none">
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
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                            <button type="submit" class="btn btn-large btn-primary" id="create-button">
                                Create Superhero/Supervillain
                            </button>
                        </div>    
                    </div>    
                </form>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/hero.js"></script>
</html>
