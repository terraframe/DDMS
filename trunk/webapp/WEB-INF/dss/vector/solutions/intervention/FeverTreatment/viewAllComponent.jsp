<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_Fever_Treatment"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.intervention.FeverTreatmentController.viewPage.mojo" />
  <mjl:columns>
    <mjl:structColumn attributeName="displayLabel">
      <mjl:attributeColumn attributeName="defaultLocale">
      </mjl:attributeColumn>
    </mjl:structColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="treatmentName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink display="View" action="dss.vector.solutions.intervention.FeverTreatmentController.view.mojo" name="view.link">
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
<mjl:commandLink display="Create a new Fever Treatment" action="dss.vector.solutions.intervention.FeverTreatmentController.newInstance.mojo" name="FeverTreatmentController.newInstance" />
