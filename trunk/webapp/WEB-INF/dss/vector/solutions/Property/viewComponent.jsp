<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="View_All_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.Property.form.name" id="dss.vector.solutions.Property.form.id" method="POST">
  <dl>    
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="item">
      <mjl:dt attribute="propertyName">
        ${item.displayLabel}        
      </mjl:dt>
      <mjl:dt attribute="description">
        ${item.description}        
      </mjl:dt>
      <mjl:dt attribute="propertyType">
        ${item.propertyType}        
      </mjl:dt>
      <mjl:dt attribute="propertyValue">
        ${item.propertyValue}        
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.PropertyController.edit.mojo" name="dss.vector.solutions.Property.form.edit.button" classes="submitButton"/>
  </dl>
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.PropertyController.viewAll.mojo" name="dss.vector.solutions.Property.viewAll.link" />
