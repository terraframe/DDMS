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
<%@page import="dss.vector.solutions.query.CycleJobController"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.query.MappingController"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryController"%>
<%@page import="dss.vector.solutions.query.NonRangeCategoryController"%>
<%@page import="dss.vector.solutions.query.LayerViewDTO"%>
<%@page import="dss.vector.solutions.query.ThematicVariableDTO"%>

<%@page import="dss.vector.solutions.query.DefaultSavedMapDTO"%>


<%@page import="dss.vector.solutions.query.SavedMapController"%>
<%@page import="dss.vector.solutions.query.LayerDTO"%>
<%@page import="dss.vector.solutions.query.LayerController"%>
<%@page import="dss.vector.solutions.query.AbstractCategoryController"%>

<%@page import="dss.vector.solutions.query.MapImage"%>

<%@page import="dss.vector.solutions.query.SavedSearchDTO"%>
<%@page import="dss.vector.solutions.query.AttributeGeoHierarchyDTO"%>
<c:set var="page_title" value="Generate_Maps"  scope="request"/>

<jsp:include page="../templates/header.jsp"></jsp:include>

<jwr:script src="/bundles/mapBundle.js"/>

<script type="text/javascript">


<%
ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);

String[] types = new String[]{DefaultSavedMapDTO.CLASS, SavedMapController.CLASS, LayerViewDTO.CLASS, LayerDTO.CLASS,
    ThematicVariableDTO.CLASS, RangeCategoryDTO.CLASS, RangeCategoryController.CLASS, NonRangeCategoryDTO.CLASS,
    NonRangeCategoryController.CLASS, MappingController.CLASS, LayerController.CLASS, AbstractCategoryController.CLASS, SavedSearchDTO.CLASS,
    AttributeGeoHierarchyDTO.CLASS, MapImage.CLASS, CycleJobController.CLASS};

String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
out.print(js);
%>


YAHOO.util.Event.onDOMReady(function(){

  var mapList = <%= (String) request.getAttribute("mapList") %>

  new MDSS.MapPanel('mapPanel', mapList).render();
});
</script>

<div class="yui-skin-sam">
  <div id="mapPanel"></div>
</div>

<iframe id="exportIframe" name="exportIframe" style="display: none; width: 1px; height: 1px;"></iframe>
<iframe id="imageIframe" name="imageIframe" style="display: none; width: 1px; height: 1px;"></iframe>
<iframe id="mapExportIframe" name="mapExportIframe" style="display: none; width: 1px; height: 1px;"></iframe>

<div style="display: none">
  <form id="exportShapefile" target="exportIframe" method="POST" action="dss.vector.solutions.query.MappingController.exportShapefile.mojo">
    <input type="hidden" id="export_mapId" name="mapId" />
    <input type="hidden" id="export_namedMapId" name="namedMapId" />
  </form>
</div>

<jsp:include page="../templates/footer.jsp"></jsp:include>