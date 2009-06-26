<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Manage_Lethal_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.general.LethalTimeProperty.form.name" id="dss.vector.solutions.general.LethalTimeProperty.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>  
    <mjl:component item="item" param="dto">
      <mjl:input param="insecticide" type="hidden" value="${item.insecticide.id}"/>
      <mjl:dt attribute="insecticide">
          ${item.insecticide.displayLabel}      
      </mjl:dt>
      <mjl:dt attribute="lowerPercent"> ${item.lowerPercent} </mjl:dt>
      <mjl:dt attribute="lowerTime"> ${item.lowerTime} </mjl:dt>
      <mjl:dt attribute="upperPercent"> ${item.upperPercent} </mjl:dt>
      <mjl:dt attribute="upperTime"> ${item.upperTime} </mjl:dt>  
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.general.LethalTimePropertyController.edit.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.general.LethalTimePropertyController.viewAll.mojo" name="dss.vector.solutions.general.LethalTimeProperty.viewAll.link" />
