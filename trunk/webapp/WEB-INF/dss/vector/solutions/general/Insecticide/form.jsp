<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.general.InsecticideDTO"%>



<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="activeIngredient">
    <span class="clickable browserLauncher" id="activeIngredientBtn"> <fmt:message key="Browser"/></span>
    <div id="activeIngredientDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${activeIngredient != null}">
          ${activeIngredient.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input type="hidden" param="activeIngredient" id="activeIngredient" value="${activeIngredient != null ? activeIngredient.id : ''}" />
  </mjl:dt>
  <mjl:dt attribute="amount">
    <mjl:input param="amount" type="text" />
  </mjl:dt>
  <mjl:dt attribute="units">
    <mjl:select param="units" items="${units}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.unitsEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.unitsMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    new MDSS.GenericOntologyBrowser("<%=InsecticideDTO.CLASS%>", [{attributeName:'activeIngredient'}]);
  })
})();
</script>