<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<jsp:include page="/WEB-INF/selectSearch.jsp" />

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />      
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" value="${item.geoEntity}" political="false" populated="false" spray="true" />
      </mjl:dt>
      <mjl:dt attribute="brand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand" >
         <mjl:option selected="${item.brand != null && current.id == item.brand.id ? 'selected' : 'false'}">
           ${current.productName.termDisplayLabel.value}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="sprayDate">
        <mjl:input param="sprayDate" type="text" classes="DatePick NoFuture" id="sprayDate" value="${item.sprayDate}" />
      </mjl:dt>
      <mjl:dt attribute="sprayMethod">
        <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="sprayMethod">
          <mjl:groupOption checked="${mjl:contains(item.sprayMethodEnumNames, current.enumName)? 'checked' : 'false'}">
            ${current.displayLabel}
          </mjl:groupOption>
        </mjl:group>      
      </mjl:dt>      
      <mjl:dt attribute="surfaceType">
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>                  
      <mjl:dt attribute="supervisor">
        <mjl:select var="current" valueAttribute="supervisorId" items="${supervisors}" param="supervisor" includeBlank="true" >
         <mjl:option selected="${supervisor != null && current.id == supervisor.id ? 'selected' : 'false'}">
           ${current.firstName} ${current.lastName}
         </mjl:option>
        </mjl:select>        
      </mjl:dt>
    </mjl:component>