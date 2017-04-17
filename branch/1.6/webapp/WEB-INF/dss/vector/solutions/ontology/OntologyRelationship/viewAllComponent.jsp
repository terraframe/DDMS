<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.ontology.OntologyRelationshipController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="altId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="comment">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="def">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inverseOf">
      <mjl:row>
        ${item.inverseOf.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inverseOfOnInstanceLevel">
      <mjl:row>
        ${item.inverseOfOnInstanceLevel.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isAntiSymmetric">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isBuiltIn">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isObsolete">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isReflexive">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="isTransitive">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="keyName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="name">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="namespace">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="relationshipId">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.ontology.OntologyRelationshipController.view.mojo" name="view.link">
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
<mjl:commandLink  action="dss.vector.solutions.ontology.OntologyRelationshipController.newInstance.mojo" name="OntologyRelationshipController.newInstance">
<mdss:localize key="Create_a_new_Ontology_Relationship" />
</mjl:commandLink>
