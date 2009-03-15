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