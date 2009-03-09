<jsp:include page="/WEB-INF/templates/header.jsp" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="csu.mrc.ivcc.mdss.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
    String rootId = (String) request.getAttribute("rootGeoEntityId");
  
    String tree = GeoHierarchyDTO.defineAllowedTree(requestIF, rootId);
    JSONObject obj = new JSONObject(tree);
    JSONArray imports = obj.getJSONArray("imports");
    
    String[] types = new String[imports.length()];
    for(int i=0; i<types.length; i++)
    {
      types[i] = imports.getString(i);
    }
    
    String js = JSONController.importTypes(requestIF.getSessionId(), types, true);
    out.print(js);
  %>

  MDSS.GeoTreeSelectables = <%= tree %>;

  YAHOO.util.Event.onDOMReady(function(){
    
        var request = new Mojo.ClientRequest({
          onSuccess : function(geoEntity){
            // build tree
            MDSS.GeoEntityTree.initializeTree("treeView", geoEntity);
          },
          onFailure : function(e){
            alert(e.getLocalizedMessage());
          }
        });
    
        // Fetch the root node
        Mojo.$.csu.mrc.ivcc.mdss.geo.generated.GeoEntity.get(request, '<%= rootId %>');
  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>
<jsp:include page="/WEB-INF/templates/footer.jsp" />