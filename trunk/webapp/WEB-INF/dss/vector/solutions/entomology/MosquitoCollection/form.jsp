<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>

<jsp:include page="/WEB-INF/MOSearch.jsp" />

    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="collectionMethod">
        <span class="clickable" id="collectionMethodBtn"> <fmt:message key="Browser"/></span>
        <div id="collectionMethodDisplay" class="ontologyDisplay">
          <c:if test="${collectionMethod != null}">
            ${collectionMethod.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="collectionMethod" id="collectionMethod" value="${collectionMethod != null ? collectionMethod.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="dateCollected" type="text" classes="DatePick" />
      <dt><label> ${item.geoEntityMd.displayLabel} </label></dt>
      <dd>${item.geoEntity.displayString} 
       <mjl:input type="hidden" param="geoEntity" id="dto.geoEntity.id" value="${item.geoEntity.id}" /> <mjl:messages attribute="geoEntity">
        <mjl:message />
      </mjl:messages></dd>
    </mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    new MDSS.GenericOntologyBrowser("<%=MosquitoCollectionDTO.CLASS%>", [{attributeName:'collectionMethod'}]);
  })
})();
</script>