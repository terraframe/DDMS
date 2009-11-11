<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.stock.StockItemViewDTO"%>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="itemId">
    <mjl:input type="text" param="itemId"/>
  </mjl:dt>
  <mjl:dt attribute="itemName">
    <span id="itemNameBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="itemNameDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${itemName != null}">
          ${itemName.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="itemName" param="itemName" value="${itemName != null ? itemName.id : ''}" type="hidden" />
  </mjl:dt>
  <mjl:dt attribute="quantity">
    <mjl:input param="quantity" type="text" />
  </mjl:dt>
  <mjl:dt attribute="unit">
    <span id="unitBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="unitDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${unit != null}">
          ${unit.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="unit" param="unit" value="${unit != null ? unit.id : ''}" type="hidden" />
  </mjl:dt>
</mjl:component>
<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    var attributes = [
         {attributeName:'itemName'},
         {attributeName:'unit'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=StockItemViewDTO.CLASS%>", attributes);
  })
})();
</script>