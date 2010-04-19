<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Configure_Case_Surveillance"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<dt>
  <label><fmt:message key="Configure_Case_Properties"/></label>
</dt>
<dd>
<mjl:table var="item" query="${properties}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="displayLabel">
      <mjl:header />
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="description">
      <mjl:header />
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="propertyValue">
      <mjl:header />
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>

        <mjl:form name="dss.vector.solutions.Property.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mjl:command value="Edit" action="dss.vector.solutions.PropertyController.edit.mojo" name="dss.vector.solutions.Property.form.edit.button" classes="submitButton" />
        </mjl:form>
      </mjl:row>
      <mjl:footer>

      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
</dd>

<dt>
  <label><fmt:message key="View_All_Age_Group"/></label>
</dt>
<dd>
<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="displayLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="active">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startAge">
    </mjl:attributeColumn>    
    <mjl:attributeColumn attributeName="endAge">
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.view.mojo" name="view.link">
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
</dd>
