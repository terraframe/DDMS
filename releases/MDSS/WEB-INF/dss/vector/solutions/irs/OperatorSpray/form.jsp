<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="sprayId" value="${item.sprayId}" />      
      <mjl:input type="hidden" param="sprayDate" value="${item.sprayDate}" classes="DatePick" />      
      <mjl:input type="hidden" param="brand" value="${item.brand.id}" />      
      <mjl:input type="hidden" param="geoEntity" value="${item.geoEntity.id}" />      
      <mjl:input type="hidden" param="sprayOperator" value="${item.sprayOperator.id}" />      
      <mjl:input type="hidden" param="sprayMethod" value="${item.sprayMethodEnumNames[0]}"/>  

      <mjl:dt attribute="geoEntity">
        ${item.geoEntity.geoId}
      </mjl:dt>
      <mjl:dt attribute="brand"> 
        ${brand.brandName}
      </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <span id="testDateSpan" class="formatDate">${item.sprayDate}</span>
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <ul>
          <c:forEach var="enumName" items="${item.sprayMethodEnumNames}">
            <li>${item.sprayMethodMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>            
      </mjl:dt>
      <mjl:dt attribute="sprayOperator">
        ${item.sprayOperator.person.lastName}, ${item.sprayOperator.person.firstName}
      </mjl:dt>      
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
      <mjl:dt attribute="operatorSprayWeek" type="text" />
      <mjl:dt attribute="received" type="text"/>
      <mjl:dt attribute="refills" type="text"/>
      <mjl:dt attribute="returned" type="text"/>
      <mjl:dt attribute="used" type="text"/>
    </mjl:component>
