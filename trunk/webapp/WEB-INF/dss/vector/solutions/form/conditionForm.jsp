<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<mjl:component item="${condition}" param="condition">
<dl>
  <mjl:dt attribute="definingMdField">
    ${definingMdFieldDisplay}
    <mjl:input type="hidden" param="definingMdField" value="${definingMdField}" ></mjl:input> 
  </mjl:dt>
  
  <c:if test="${operations != null}">
  
	  <mjl:dt attribute="operation">
	    <mjl:select items="${operations}" param="operation" valueAttribute="enumName" var="current">
	      <mjl:option selected="${mjl:contains(condition.operationEnumNames, current.enumName) ? 'selected' : 'false'}">
	        ${current.displayLabel}
	      </mjl:option>
	    </mjl:select>
	  </mjl:dt>

  </c:if>
  
  <mjl:dt attribute="value">
    <mjl:input type="text" param="value" value="${condition.value}" id="${condition.id}_value"></mjl:input> 
  </mjl:dt>
</dl>
</mjl:component>

<c:if test="${includeCalendar}">
	<script type="text/javascript">
	(function(){
	  var el = document.getElementById('${condition.id}_value');
	  el.value = MDSS.Calendar.getLocalizedString(el.value);
	  MDSS.Calendar.addCalendarListeners(el);
	})();
	</script>
</c:if>