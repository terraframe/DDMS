<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_RelationshipTypes" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.ontology.RelationshipTypesController.viewPage.mojo" />
  <mjl:columns>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:header>
        Display Label
      </mjl:header>
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="enumName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.ontology.RelationshipTypesController.view.mojo" name="view.link">
          <fmt:message key="View" />
          <mjl:property value="${item.id}" name="id" />
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
<mjl:commandLink action="dss.vector.solutions.ontology.RelationshipTypesController.newInstance.mojo" name="RelationshipTypesController.newInstance">
  <fmt:message key="Create_a_new_MO_Relationships" />
</mjl:commandLink>
