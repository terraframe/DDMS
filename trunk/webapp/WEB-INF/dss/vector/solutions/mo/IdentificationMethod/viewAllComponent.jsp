<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Identification_Method_View_All" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.mo.IdentificationMethodController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="definition">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="enabled">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTerm">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="inheritsTermName">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="oboNamespace">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termId">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="termName">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.mo.IdentificationMethodController.view.mojo" name="view.link">
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
<mjl:commandLink action="dss.vector.solutions.mo.IdentificationMethodController.newInstance.mojo" name="IdentificationMethodController.newInstance">
<fmt:message key="Create_a_new_Identification_Method" />
</mjl:commandLink>

