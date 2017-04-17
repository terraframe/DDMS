<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_All_Properties" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table classes="displayTable" var="item" query="${query}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.entomology.ResistancePropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="displayLabel">
      <mjl:row>
        ${item.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>  
    <mjl:attributeColumn attributeName="description">
      <mjl:row>
        ${item.description}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="propertyValue">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:form name="${item.id}.form" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mdss:localize key="Edit" var="Localized_Edit" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.entomology.ResistancePropertyController.edit.mojo" name="${item.id}.button" classes="submitButton" />
        </mjl:form>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
<br />