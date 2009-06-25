<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoEntityTreeComponent.jsp"></jsp:include>

<input type="button" value="Regenerate Paths" id="regeneratePaths" />

<script type="text/javascript">
  YAHOO.util.Event.on('regeneratePaths', 'click', function(){
    
    var request = new MDSS.Request({
      onSuccess : function(){ /*success*/} 
    });
    
    Mojo.$.dss.vector.solutions.geo.AllPaths.regeneratePaths(request);
  });
</script>

<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){

    MDSS.GeoEntityTree.initializeTree("treeView");

  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />