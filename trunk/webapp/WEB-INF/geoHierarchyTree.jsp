
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="Configure_the_Universal_Tree"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoHierarchyTreeComponent.jsp"></jsp:include>

<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){

    MDSS.GeoHierarchyTree.initializeTree("treeView");

  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />