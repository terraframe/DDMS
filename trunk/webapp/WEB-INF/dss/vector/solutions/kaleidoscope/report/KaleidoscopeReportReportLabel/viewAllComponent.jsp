<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_KaleidoscopeReportReportLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="dENGUE_defaultLocale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="defaultLocale">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="mALARIA_defaultLocale">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelController.view.mojo">
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
<mjl:commandLink name="KaleidoscopeReportReportLabelController.newInstance" action="dss.vector.solutions.kaleidoscope.report.KaleidoscopeReportReportLabelController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Report_label" />
</mjl:commandLink>
