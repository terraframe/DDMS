
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="Manage_Geo_Entities"  scope="request"/>

<jsp:include page="/WEB-INF/templates/header.jsp" />

<jsp:include page="geoEntityTreeComponent.jsp"></jsp:include>

<form id="dss.vector.solutions.export.GeoEntityExcelView.export" name="dss.vector.solutions.export.GeoEntityExcelView.export" action="excelexport" method="post">
  <input type="hidden" value="dss.vector.solutions.export.GeoEntityExcelView" name="excelType"/>
  <input type="submit" class="submitButton" name="export.button" value="Export Excel File"/>
</form>
<script type="text/javascript">
  YAHOO.util.Event.onDOMReady(function(){

    MDSS.GeoEntityTree.initializeTree("treeView");

  }, null, true);
</script>

<div class="yui-skin-sam">
  <div id="treeView"></div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />