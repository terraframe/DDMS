<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_All_GeoUniversal" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" odd="oddRow" classes="displayTable" even="evenRow">
  <mjl:context action="dss.vector.solutions.ontology.GeoUniversalController.viewPage.mojo" />
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
        <mjl:commandLink action="dss.vector.solutions.ontology.GeoUniversalController.view.mojo" name="view.link">
          <mdss:localize key="View" />
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
<mjl:commandLink action="dss.vector.solutions.ontology.GeoUniversalController.newInstance.mojo" name="GeoUniversalController.newInstance">
  <mdss:localize key="Create_a_new_Geo_Universal" />
</mjl:commandLink>
