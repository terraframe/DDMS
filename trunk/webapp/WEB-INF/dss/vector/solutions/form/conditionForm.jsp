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
	  <c:choose>
	    <c:when test="${isTerm}">
	      <div>
	        <!--
	             Hack Alert! We have to use the name of the defining MdField as DOM properties
	             in order for the GenericOntologyBrowser to work. The name of the field input
	             is still condition.value as expected.
	         -->
	        <input type="hidden" id="${name}" name="condition.value" value="${condition.value}">
	        <input type="text" id="${name}Display" value="${termDisplayLabel}" autocomplete="off">
	        <span id="${name}Btn" class="clickable">
	          <img alt="Browser" title="Browser" src="./imgs/icons/term.png" class="ontologyOpener">
	        </span>
	      </div>    
	    </c:when>
	    <c:when test="${isGeo}">
	      <mjl:input type="text" value="${geoId}" id="${condition.id}_value" /> 
	      <mjl:input type="hidden" param="value" value="${condition.value}" id="${condition.id}_value_geoEntityId" /> 
	    </c:when>
	    <c:when test="${isBool}">
	      <mjl:boolean param="value" />
	    </c:when>
	    <c:otherwise>
	      <mjl:input type="text" param="value" value="${condition.value}" id="${condition.id}_value" /> 
	    </c:otherwise>
	  </c:choose>
  </mjl:dt>
</dl>
</mjl:component>

<c:if test="${isDate}">
	<script type="text/javascript">
	(function(){
	  var el = document.getElementById('${condition.id}_value');
	  MDSS.Calendar.addCalendarListeners(el);
	  el.value = MDSS.Calendar.getLocalizedString(el.value);
	})();
	</script>
</c:if>

<c:if test="${isTerm}">
	<script type="text/javascript">
	(function(){
    var browser = new MDSS.GenericOntologyBrowser('${type}', {
      attributeName : '${name}'
    });
	})();
	</script>
</c:if>

<c:if test="${isGeo}">
	<script type="text/javascript">
	
	(function(){
	
	  var geoField = ${geoField};
	
    var geoInput = document.getElementById('${condition.id}_value');
    var selectSearch = new MDSS.SingleSelectSearch(true);
    selectSearch.setPolitical(geoField.isPoliticalHierarchy);
    selectSearch.setPopulated(geoField.isPopulationHierarchy);
    selectSearch.setSprayTargetAllowed(geoField.isSprayHierarchy);
    selectSearch.setUrban(geoField.isUrbanHierarchy);

    // filter
    selectSearch.setFilter(geoField.filter);
        
    // extra universals
    var extra = geoField.extraUniversals;
    for(var i=0; i<extra.length; i++)
    {
      selectSearch.addExtraUniversal(extra[i]);
    }
    
    
    var geoSearch = new MDSS.GeoSearch(geoInput, selectSearch);  
	})();
	</script>
</c:if>

