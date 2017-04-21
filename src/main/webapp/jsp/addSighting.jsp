<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report A Sighting</title>
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
                                <li role="presentation"><a class="active" href="${pageContext.request.contextPath}/sighting/displayReportSightingPage">Report a sighting</a></li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Heroes<span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateHeroPage">Add Hero</a></li>
                                        <li><a href="${pageContext.request.contextPath}/retrieval/displayAllHeroesPage">Show All Heroes</a></li>
                                    </ul>
                                </li>
                                <li role="presentation"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" href="#">
                                        Powers<span class="active caret"></span></a>
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
        <sf:form role="form" class="form-horizontal" action="createSighting" method="POST">
            <div class="container">
                <div class="form-group col-md-6 col-sm-6 col-xs-6">
                    <label class="col-md-4 control-label">Hero:</label>
                    <div class="col-md-8">
                        <select path="hero" class="selectpicker form-control" name="hero">
                            <c:forEach var="hero" items="${heroes}">
                                <option class="col-md-6" path="hero" name="hero" value="${hero.heroId}">${hero.alias}</option>
                                <%--<sf:errors path="hero" cssclass="error"></sf:errors>--%>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6 col-sm-6 col-xs-6">
                    <label class="col-md-4 control-label">Location:</label>
                    <div class="col-md-8">
                        <select path="location" class="selectpicker form-control" id="location" name="location">
                            <c:forEach var="location" items="${locations}">
                                <option class="col-md-6" path="location" name="location" value="${location.locationId}">${location.locationName}</option>
                                <%--<sf:errors path="location" cssclass="error"></sf:errors>--%>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                    <label>Sighted Time:</label>
                </label>
                <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
                    <input type="datetime-local" class="add-form form-control" path="sightingDate" name="sightingDate" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="Description" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                    <label>Description:</label>
                </label>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <textarea class="form-control" path="description" name="description" row="5"></textarea>
                </div>
            </div>
            <div class="form-group">
              <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                      <button type="submit" class="btn btn-large btn-primary" id="create-button">
                          Report a sighting
                      </button>
                  </div>    
              </div> 
            <hr>
              <div class="form-group">
                  <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                      <a href="${pageContext.request.contextPath}/retrieval/displayAllSightingsPage">
                          <button type='button' id='cancel-button' class='btn btn-default'>
                              Display All Sightings
                          </button>
                      </a>
                  </div>    
              </div> 
            </sf:form>
            </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>
    </body>
</html>