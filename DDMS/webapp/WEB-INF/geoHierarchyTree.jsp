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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="dss.vector.solutions.geo.generated.EarthDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%><c:set var="page_title" value="Configure_the_Universal_Tree"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoHierarchyTreeComponent.jsp"></jsp:include>

<script type="text/javascript">
<% 
  ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
%>
  YAHOO.util.Event.onDOMReady(function(){

	  var tree = new MDSS.GeoHierarchyTree("treeView", MDSS.DefaultRoot);
	  tree.render();

	  
    //MDSS.GeoHierarchyTree.initializeTree("treeView");

    window.onbeforeunload = function(){
       //start process of refreshing the allowed in tree, 
       //and don't worry about errors or returns.
      var request = new MDSS.Request({
        onSuccess : function(){},
        onFailure : function(){},
        onSend : function(){},
        onComplete : function(){}
      });

      Mojo.$.dss.vector.solutions.geo.GeoHierarchy.defineAllowedTree(request, '<%= EarthDTO.getEarthInstance(requestIF).getId() %>');
    };
  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />