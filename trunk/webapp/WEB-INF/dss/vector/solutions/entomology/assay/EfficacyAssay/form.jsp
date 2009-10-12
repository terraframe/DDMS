<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO"%><jsp:include page="/WEB-INF/MOSearch.jsp" />

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:dt attribute="geoId">
        <mjl:input id="geoIdEl" param="geoId" type="text" value="${item.geoId}" maxlength="16" classes="geoInput"/>
      </mjl:dt>
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="testMethod">
        <span class="clickable" id="testMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="testMethodDisplay" class="ontologyDisplay">
          <c:if test="${testMethod != null}">
            ${testMethod.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="testMethod" id="testMethod" value="${testMethod != null ? testMethod.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="specie">
        <span class="clickable" id="specieBtn"> <fmt:message key="Browser"/></span>
        <div id="specieDisplay" class="ontologyDisplay">
          <c:if test="${specie != null}">
            ${specie.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="specie" id="specie" value="${specie != null ? specie.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="colonyName">
        <mjl:input type="text" param="colonyName" />
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <dl>
          <mjl:struct param="ageRange">
            <mjl:dt attribute="startPoint" type="text"  />
            <mjl:dt attribute="endPoint" type="text"  />
          </mjl:struct>
        </dl>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <span class="clickable" id="sexBtn"> <fmt:message key="Browser"/></span>
        <div id="sexDisplay" class="ontologyDisplay">
          <c:if test="${sex != null}">
            ${sex.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="sex" id="sex" value="${sex != null ? sex.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="insecticide">
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
        <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><fmt:message key="Manage_Insecticides" /></a>
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface" >
        <mjl:input type="text" param="timeOnSurface" />
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">
        <span class="clickable" id="surfacePostionBtn"> <fmt:message key="Browser"/></span>
        <div id="surfacePostionDisplay" class="ontologyDisplay">
          <c:if test="${surfacePostion != null}">
            ${surfacePostion.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="surfacePostion" id="surfacePostion" value="${surfacePostion != null ? surfacePostion.id : ''}" />
      </mjl:dt>      
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>
    </mjl:component>

    <script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'testMethod'},
         {attributeName:'specie'},
         {attributeName:'sex'},
         {attributeName:'surfacePostion'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=EfficacyAssayViewDTO.CLASS%>", attributes);
  })
})();

</script>    