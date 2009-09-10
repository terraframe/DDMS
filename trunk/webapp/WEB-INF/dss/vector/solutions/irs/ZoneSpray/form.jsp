<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="sprayId" value="${item.sprayId}" />      
      <mjl:dt attribute="geoEntity">
        <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput" value="${item.geoEntity.geoId}"/>        
        <mjl:input id="geoEntityId" param="geoEntity" type="hidden" value="${item.geoEntity.id}"/>        
      </mjl:dt>
      <mjl:dt attribute="brand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand" >
         <mjl:option selected="${item.brand != null && current.id == item.brand.id ? 'selected' : 'false'}">
           ${current.brandName}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <mjl:input param="sprayDate" type="text" classes="DatePick NoFuture" id="sprayDate" value="${item.sprayDate}" />
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <mjl:radioGroup var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="sprayMethod">
          <mjl:radioOption checked="${mjl:contains(item.sprayMethodEnumNames, current.enumName)? 'checked' : 'false'}">
            ${current.displayLabel}
          </mjl:radioOption>
        </mjl:radioGroup>      
      </mjl:dt>      
      <mjl:dt attribute="surfaceType">       
        <mjl:select var="current" valueAttribute="enumName" items="${surfaceTypes}" param="surfaceType">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </mjl:dt>        
      <mjl:dt attribute="sprayWeek" type="text"/>
      <mjl:dt attribute="supervisorName" type="text"/>
      <mjl:dt attribute="supervisorSurname" type="text"/>
      <mjl:dt attribute="target" type="text"/>
    </mjl:component>
