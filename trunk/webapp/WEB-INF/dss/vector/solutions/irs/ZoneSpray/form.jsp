<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.irs.AbstractSprayViewDTO"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />      
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" value="${item.geoEntity}" political="false" populated="false" spray="true" />
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
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>                  
      <mjl:dt attribute="sprayWeek" type="text"/>
      <mjl:dt attribute="supervisor">
        <mjl:select var="current" valueAttribute="supervisorId" items="${supervisors}" param="supervisor" includeBlank="true" >
         <mjl:option selected="${supervisor != null && current.id == supervisor.id ? 'selected' : 'false'}">
           ${current.firstName} ${current.lastName}
         </mjl:option>
        </mjl:select>        
      </mjl:dt>
      <mjl:dt attribute="target" type="text"/>
    </mjl:component>