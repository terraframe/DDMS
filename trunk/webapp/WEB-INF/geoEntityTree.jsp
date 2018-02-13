<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="Manage_Geo_Entities"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoEntityTreeComponent.jsp"></jsp:include>

<form id="dss.vector.solutions.export.GeoEntityExcelView.export" name="dss.vector.solutions.export.GeoEntityExcelView.export" action="excelexport" method="post">
  <input type="hidden" value="dss.vector.solutions.export.GeoEntityExcelView" name="excelType"/>
  <mdss:localize key="Excel_Export_Header" var="export_label"/>
  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
</form>

<input type="text" id="geoSearchBar" />
<input type="hidden" id="geoSearchBar_geoEntityId" />

<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){
    
    var tree = new MDSS.GeoEntityTree("treeView", null, MDSS.DefaultRoot);
    
    var searchBarSelectHandler = function(view) {
      tree.searchForGeo(view.getGeoEntityId(), view);
    };
    
    var geoSearch = new MDSS.GeoSearch(document.getElementById("geoSearchBar"), {
      filter : "",
      enforceRoot : false,
      political : false,
      populated : false,
      sprayTargetAllowed : false,
      urban : false,
      extraUniversals : []
    });
    geoSearch.setSelectEventHandler(searchBarSelectHandler);
    geoSearch.setIsFormInput(false);

    tree.allowSearchingForGeos();
    tree.render();
  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />