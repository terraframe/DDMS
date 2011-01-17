<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_Properties" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
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
    </mjl:freeColumn>
  </mjl:columns>

</mjl:table>
<br />
<c:if test="${query2 != null}">
<mjl:table classes="displayTable" var="item" query="${query2}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.DefaultGeoEntityController.viewPage.mojo" />
  <mjl:columns>
  
    <mjl:freeColumn>
      <mjl:header>
        <mdss:localize key="Display_Label" />
      </mjl:header>
      <mjl:row>
        <mdss:localize key="Default_Geo_Root" />
      </mjl:row>
      
    </mjl:freeColumn>
  
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.displayString}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.Property.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mjl:command value="Edit" action="dss.vector.solutions.DefaultGeoEntityController.edit.mojo" name="dss.vector.solutions.DefaultGeoEntityController.edit.btn" classes="submitButton" />
        </mjl:form>        
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
</c:if>
<br />