<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="sprayId" value="${item.sprayId}" />      
      <mjl:input type="hidden" param="sprayDate" value="${item.sprayDate}" classes="DatePick" />      
      <mjl:input type="hidden" param="brand" value="${item.brand.id}" />      
      <mjl:input type="hidden" param="geoEntity" value="${item.geoEntity.id}" />      
      <mjl:input type="hidden" param="sprayTeam" value="${item.sprayTeam.id}" />      
      <mjl:input type="hidden" param="sprayMethod" value="${item.sprayMethodEnumNames[0]}"/>        
      
      <mjl:dt attribute="teamLeader">       
        <mjl:select var="current" valueAttribute="id" items="${operators}" param="teamLeader">
          <mjl:option>
            ${current.person.lastName}, ${current.person.firstName}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
      
      <mjl:dt attribute="surfaceType">       
        <mjl:select var="current" valueAttribute="enumName" items="${surfaceTypes}" param="surfaceType">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
      <mjl:dt attribute="teamSprayWeek" type="text"/>
      <mjl:dt attribute="target" type="text"/>
    </mjl:component>
