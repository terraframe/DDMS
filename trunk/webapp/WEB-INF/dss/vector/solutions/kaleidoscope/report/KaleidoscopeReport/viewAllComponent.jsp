<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_KaleidoscopeReport" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="cacheDocument">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="dashboard">
      <mjl:row>
        ${item.dashboard.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="design">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="document">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="reportLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="reportName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink name="KaleidoscopeReportController.newInstance" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Report_item" />
</mjl:commandLink>
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
