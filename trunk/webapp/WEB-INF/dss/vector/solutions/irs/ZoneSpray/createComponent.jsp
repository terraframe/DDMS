<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <dl>
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="sprayId" value="${item.sprayId}" />      
      <mjl:input type="hidden" param="sprayDate" value="${item.sprayDate}" classes="DatePick" />      
      <mjl:input type="hidden" param="brand" value="${item.brand.id}" />      
      <mjl:input type="hidden" param="geoEntity" value="${item.geoEntity.id}" />      
      <mjl:input type="hidden" param="sprayMethod" value="${item.sprayMethodEnumNames[0]}"/>  
      
      <mjl:dt attribute="surfaceType">       
        <mjl:select var="current" valueAttribute="enumName" items="${surfaceTypes}" param="surfaceType">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
    </mjl:component>
  </dl>
  <mjl:command value="Create" action="dss.vector.solutions.irs.ZoneSprayController.create.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.create.button" />
</mjl:form>
