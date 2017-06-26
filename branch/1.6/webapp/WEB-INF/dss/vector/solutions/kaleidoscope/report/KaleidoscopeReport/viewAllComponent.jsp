<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>

<head>

<!-- Report CSS -->
<jwr:style src="/bundles/datatable.css" useRandomParam="false"/>  
<jwr:style src="/net/geoprism/report/ReportTable.css" useRandomParam="false"/>  

<!-- Report Javascript -->
<jwr:script src="/bundles/datatablejquery.js" useRandomParam="false"/>
<jwr:script src="/bundles/datatable.js" useRandomParam="false"/>
<jwr:script src="/bundles/runway-controller.js" useRandomParam="false"/>

<!-- 
<jwr:script src="/bundles/report.js" useRandomParam="false"/>

<jwr:script src="/net/geoprism/report/ReportItem.js" useRandomParam="false"/>
<jwr:script src="/net/geoprism/report/ReportItemView.js" useRandomParam="false"/>
 -->


</head>

<div id="reportTable"></div>

<!-- 
<script type="text/javascript">
  com.runwaysdk.ui.Manager.setFactory("JQuery");
  
  $(document).ready(function() {
  		var ut = new net.geoprism.report.ReportTable({"bAutoWidth":false});  
  		ut.render("#reportTable");
  });  
</script>
 -->
