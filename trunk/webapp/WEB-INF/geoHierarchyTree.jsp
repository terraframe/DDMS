
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