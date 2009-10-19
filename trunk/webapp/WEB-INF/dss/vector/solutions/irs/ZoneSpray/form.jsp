<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.irs.AbstractSprayViewDTO"%><jsp:include page="/WEB-INF/selectSearch.jsp" />

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
        <span class="clickable browserLauncher" id="surfaceTypeBtn"> <fmt:message key="Browser"/></span>
        <div id="surfaceTypeDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${surfaceType != null}">
              ${surfaceType.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="surfaceType" id="surfaceType" value="${surfaceType != null ? surfaceType.id : ''}" />
      </mjl:dt>                  
      <mjl:dt attribute="sprayWeek" type="text"/>
      <mjl:dt attribute="supervisorName" type="text"/>
      <mjl:dt attribute="supervisorSurname" type="text"/>
      <mjl:dt attribute="target" type="text"/>
    </mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'surfaceType'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=AbstractSprayViewDTO.CLASS%>", attributes);
  })
})();
</script>