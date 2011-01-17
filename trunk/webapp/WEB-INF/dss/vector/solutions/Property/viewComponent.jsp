<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<c:choose>
  <c:when test="${!isCaseConfiguration}">
    <mjl:commandLink action="dss.vector.solutions.PropertyController.viewAll.mojo" name="viewAll.link" id="viewAll">
      <mdss:localize key="View_All" />
    </mjl:commandLink>  
  </c:when>
  <c:otherwise>
    <mjl:commandLink action="dss.vector.solutions.surveillance.AggregatedAgeGroupController.viewAll.mojo" name="viewAll.link" id="viewAll">
      <mdss:localize key="View_All" />
    </mjl:commandLink>  
  </c:otherwise>
</c:choose>