<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization List</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">   
        <link href="${pageContext.request.contextPath}/css/general.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
        <style>
            th, a{
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
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllHeroesPage"> Show All Heroes</a></li>
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
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllOrganizationsPage">> Show All Organizations</a></li>
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
        <div class="container">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th width="20%"></th>
                            <th width="20%">Organization Name</th>
                            <th width="15%">Edit</th>
                            <th width="15%">Delete</th>
                            <th width="20%"></th>
                        </tr>
                    </thead>
                    <c:forEach var="org" items="${orgs}">
                        <tr>
                            <td></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/retrieval/displayOrgDetails?orgId=${org.orgId}">
                                    <c:out value="${org.orgName}"/>
                                </a>
                            </td>
                            <td>
                                <a class="glyphicon glyphicon-pencil white" 
                                    href="${pageContext.request.contextPath}/edit/displayEditOrganization?orgId=${org.orgId}">
                                </a>
                            </td>
                            <td>
                                <a class="glyphicon glyphicon-trash white"
                                    href="${pageContext.request.contextPath}/delete/organization?orgId=${org.orgId}"
                                    onclick="return confirm('Deleting this organization will affect the affliation of all heroes whos currently in this organization.  Are you sure you want to delete this Organization?');">
                                </a>
                            </td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
