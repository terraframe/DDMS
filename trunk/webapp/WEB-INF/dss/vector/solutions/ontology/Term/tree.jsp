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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.ontology.TermViewDTO"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>
<%@page import="dss.vector.solutions.ontology.TermDTO"%>
<%@page import="dss.vector.solutions.ontology.TermController"%>
<c:set var="page_title" value="Ontology_Admin"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="/WEB-INF/selectSearch.jsp"/>
<c:set var="entity" scope="request" value="" />


<%-- 
MO SEARCH CODE GENERATED (and tweaked) FROM:
<mdss:mo param="searchTerm" value="${entity}" enabled="true" />
--%>
 <input name="searchTerm" id="searchTerm" type="hidden" class="null" />
<input name="#_searchTerm" id="searchTermDisplay" type="text" />
<span id="searchTermBtn" class="clickable ontology-clickable">
<img alt="Browser" title="Browser" src="./imgs/icons/term.png" class="ontologyOpener">
</span>
<script type="text/javascript">

<% 
String[] types = new String[]{TermViewDTO.CLASS, TermDTO.CLASS, TermController.CLASS};
ClientRequestIF clientRequest = (ClientRequestIF) request.getAttribute(ClientConstants.CLIENTREQUEST);
String js = JSONController.importTypes(clientRequest.getSessionId(), types, true);
out.write(js);
%>

YAHOO.util.Event.onDOMReady(function(){
  var browser = new MDSS.GenericOntologyBrowser('null', [{attributeName:'searchTerm', enabled:true, defaultRoot:true}]);
  //browser.addRoot(['imykwwn14kykf3ngwsbuhyogbac12qknit39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['ip44401x96t93fh7826ufs7r8xr6kwu5it39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['iok0haqosy2f4az99k1w2r0d5s8qmlp2it39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['ino8kz2rfjunn7se9x3c41w6do6h6mzait39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['ig77zpora4dzbudinl3wk534kttup6dhit39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['iby6ii46689i2hcau38w71e0f8yz3w0iit39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['i3zec781v9g55tw2w27t91jmvd6l5eq1it39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  //browser.addRoot(['iemxaa34lgbxcyl7vll92qoydrf9g428it39r46d6b0jvsgfqaf9sznc9fybsgi6','true']);
  
  var tree = new MDSS.OntologyTree('treeView');
  browser.setOntologyTree(tree);
  tree.render();
})
  
</script>

<div class="yui-skin-sam">
  <div id="treeView" style="margin: 10px"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />