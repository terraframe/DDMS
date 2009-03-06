<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@page import="csu.mrc.ivcc.mdss.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Geo Entity Tree</title>

<style type="text/css">
 .dragging, .drag-hint {
   border: 1px solid gray;
   background-color: blue;
   color: white;
   opacity: 0.76;
   filter: "alpha(opacity=76)";
 }
</style>

<jsp:include page="WEB-INF/templates/yuiIncludes.jsp" />

<script type="text/javascript" src="js/GeoEntityTree.js"></script>
<script type="text/javascript">

  <%
    ClientRequestIF requestIF = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
  
    String tree = GeoHierarchyDTO.defineAllowedTree(requestIF, "2abimwt4m9u30hgm0kq6u3yqkgt4xvzzzbh1cp06dqxflo39193g1nbuge55pngp");
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
        Mojo.$.csu.mrc.ivcc.mdss.geo.generated.GeoEntity.get(request, "zd889zpj3zd1zfqyv5l0rnflyvh1hgri939cd4afk7idpdopdlhgvk55m2p0se9k");
  }, null, true);
</script>

</head>
<body class="yui-skin-sam">
  <div id="treeView"></div>
</body>
</html>