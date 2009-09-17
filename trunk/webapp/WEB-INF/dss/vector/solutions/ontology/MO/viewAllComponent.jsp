<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_MO" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.ontology.MOController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="description">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="obsolete">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="ontology">
      <mjl:row>
        ${item.ontology.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termComment">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.ontology.MOController.view.mojo">
          <fmt:message key="View" />
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
<mjl:commandLink name="MOController.newInstance" action="dss.vector.solutions.ontology.MOController.newInstance.mojo">
  <fmt:message key="Create_a_new_MO_Term" />
</mjl:commandLink>
