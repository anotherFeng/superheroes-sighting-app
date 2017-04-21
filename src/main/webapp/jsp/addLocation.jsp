<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Location</title>
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
                                        <li><a href="${pageContext.request.contextPath}/heroesPower/displayCreateLocationPage">> Add Location</a></li>
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
            <form class="form-horizontal" role="form" action="createLocation" method="POST">
                <div class="form-group" id="map-canvas"></div>
                <div class="form-group">
                    <label for="Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Location:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="locationName" placeholder="Name" name="locationName" required/>
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
                    <label for="Latitude" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Latitude: 
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="latitude" placeholder="Latitude" name="latitude" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Longitude" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Longitude: 
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="longitude" placeholder="Longitude" name="longitude" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Address:" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Address: 
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="address" placeholder="Address" name="address"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="City" class="col-md-4 col-sm-4 col-xs-12 control-label control-label" >
                        City:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="city" placeholder="City's Name" name="city" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="State" class="col-md-4 col-sm-4 col-xs-12 control-label control-label" >
                        State/Province:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="state" placeholder="State's Name" name="state" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Country's Name" class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Country:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="country" placeholder="Country's Name" name="country"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        Zip:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="zip" placeholder="Zip-Code" name="zip"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 col-sm-4 col-xs-12 control-label control-label">
                        World:
                    </label>
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <input type="text" class="add-form form-control" path="world" placeholder="World" name="world"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-4 col-sm-offset-4 col-sm-4">
                        <button type="submit" class="btn btn-large btn-primary" id="create-button">
                            Create Location
                        </button>
                    </div>    
                </div>    
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script scr="http:"
        <script type="text/javascript">
          var map;
          function initialize() {
              var myLatlng = new google.maps.LatLng(<?=$decimalValueLon?>,<?=$decimalValueLat?>);
              var myOptions = {
              zoom: 17,
              center: myLatlng,
              mapTypeId: google.maps.MapTypeId.SATELLITE};


            map = new google.maps.Map(document.getElementById('map_canvas'),
                myOptions);

            // marker STARTS    
            var marker = new google.maps.Marker({
            position: myLatlng, 
            title:"Click to view info!"
        });  
            marker.setMap(map);
            // marker ENDS

            // info-window STARTS   
            var infowindow = new google.maps.InfoWindow({ content: "<div class='map_bg_logo'><span style='color:#1270a2;'><b><?=$row->bridge_name?></b> (<?=$row->bridge_no?>)</span><div style='border-top:1px dotted #ccc; height:1px;  margin:5px 0;'></div><span style='color:#555;font-size:11px;'><b>Length: </b><?=$row->bridge_length?> meters</span></div>" });
            google.maps.event.addListener(marker, 'click', function() {
                infowindow.open(map,marker);
            });
            // info-window ENDS 
         }

          google.maps.event.addDomListener(window, 'load', initialize);
        </script>` 
    </body>
</html>