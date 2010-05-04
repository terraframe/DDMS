<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set scope="request" var="page_title" value="Configure_immature_thresholds" />

<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.ImmatureThresholdController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="thresholdValue">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.entomology.ImmatureThresholdController.view.mojo">
          <fmt:message key="View" />
          <mjl:property name="id" value="${item.concreteId}" />
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