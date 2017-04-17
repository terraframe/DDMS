<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_LocalProperty" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.LocalPropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="description">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="editable">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyPackage">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyType">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyValidator">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyValue">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="validValues">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.LocalPropertyController.view.mojo">
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
<mjl:commandLink name="LocalPropertyController.newInstance" action="dss.vector.solutions.LocalPropertyController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Property" />
</mjl:commandLink>
