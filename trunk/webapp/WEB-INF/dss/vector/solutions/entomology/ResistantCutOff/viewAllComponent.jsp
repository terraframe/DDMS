<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.entomology.ResistantCutOffController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="aAKnockDownPR">
      <mjl:header>
        Adult Assay Knock Down for Potentially Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="aAKnockDownR">
      <mjl:header>
        Adult Assay Knock Down for Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="aDDAR">
      <mjl:header>
        Adult Diagnostic Dose Assay Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="aDDAS">
      <mjl:header>
        Adult Diagnostic Dose Assay Potentially Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lAKnockDownPR">
      <mjl:header>
        Larvae Assay Knock Down for Potentially Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lAKnockDownR">
      <mjl:header>
        Larvae Assay Knock Down for Resistant Lower Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lDDAR">
      <mjl:header>
        Larvae Diagnostic Dose Assay Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="lDDAS">
      <mjl:header>
        Larvae Diagnostic Dose Assay Potentially Resistant Limit
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.entomology.ResistantCutOffController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Property" action="dss.vector.solutions.entomology.ResistantCutOffController.newInstance.mojo" name="ResistantCutOffController.newInstance" />
