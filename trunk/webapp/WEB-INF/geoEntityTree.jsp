<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoEntityTreeComponent.jsp"></jsp:include>

<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){

    MDSS.GeoEntityTree.initializeTree("treeView");

  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />