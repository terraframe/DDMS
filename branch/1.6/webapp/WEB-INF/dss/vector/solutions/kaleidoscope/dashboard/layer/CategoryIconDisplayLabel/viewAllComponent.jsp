<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_All_CategoryIconDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.dashboard.layer.CategoryIconDisplayLabelController.viewPage.mojo" />
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
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.dashboard.layer.CategoryIconDisplayLabelController.view.mojo">
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
<mjl:commandLink name="CategoryIconDisplayLabelController.newInstance" action="dss.vector.solutions.kaleidoscope.dashboard.layer.CategoryIconDisplayLabelController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Label" />
</mjl:commandLink>
