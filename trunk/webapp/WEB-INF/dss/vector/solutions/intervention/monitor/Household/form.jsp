<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.PropertyDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.intervention.monitor.HouseholdDTO"%>



<%@page import="dss.vector.solutions.intervention.monitor.HouseholdViewDTO"%>

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:input type="hidden" param="surveyPoint" value="${item.surveyPoint.id}" />

      <mjl:dt attribute="householdName">
        <mjl:input type="text" param="householdName" id="householdId"/><a href="#" id="getUniqueId"><fmt:message key="Get_Unique_Id"/></a>
      </mjl:dt>

      <mjl:dt attribute="urban">
        <mjl:boolean param="urban" />
      </mjl:dt>

      <mjl:dt attribute="people">
        <mjl:input type="text" param="people" />
      </mjl:dt>

      <mjl:dt attribute="wall">
        <span class="clickable browserLauncher" id="wallBtn"> <fmt:message key="Browser"/></span>
        <div id="wallDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${wall != null}">
              ${wall.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="wall" id="wall" value="${wall != null ? wall.id : ''}" />
      </mjl:dt>

      <mjl:dt attribute="wallInfo">
        <mjl:input type="text" param="wallInfo" />
      </mjl:dt>

      <mjl:dt attribute="roof">
        <span class="clickable browserLauncher" id="roofBtn"> <fmt:message key="Browser"/></span>
        <div id="roofDisplay" class="ontologyDisplay">
          <c:choose>
            <c:when test="${roof != null}">
              ${roof.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input type="hidden" param="roof" id="roof" value="${roof != null ? roof.id : ''}" />
      </mjl:dt>

      <mjl:dt attribute="roofInfo">
        <mjl:input type="text" param="roofInfo" />
      </mjl:dt>

      <mjl:dt attribute="hasWindows">
        <mjl:boolean param="hasWindows" id="hasWindows"/>
      </mjl:dt>
      
      <div class="windowType">
        <mjl:dt attribute="windowType">
          <span class="clickable browserLauncher" id="windowTypeBtn"> <fmt:message key="Browser"/></span>
          <div id="windowTypeDisplay" class="ontologyDisplay">
            <c:choose>
              <c:when test="${windowType != null}">
                ${windowType.displayLabel}
              </c:when>
              <c:otherwise>
                <fmt:message key="no_value" />
              </c:otherwise>
            </c:choose>
          </div>
          <mjl:input type="hidden" param="windowType" id="windowType" value="${windowType != null ? windowType.id : ''}" />
        </mjl:dt>
      </div>

      <mjl:dt attribute="rooms">
        <mjl:input type="text" param="rooms" />
      </mjl:dt>
      
      <mjl:dt attribute="hasBeenSprayed">
        <mjl:radioGroup param="hasBeenSprayed" items="${hasBeenSprayed}" var="current" valueAttribute="enumName">
          <mjl:radioOption checked="${mjl:contains(item.hasBeenSprayedEnumNames, current.enumName) ? 'checked' : 'false'}" id="hasBeenSprayed.${current.enumName}">
            ${item.hasBeenSprayedMd.enumItems[current.enumName]}
          </mjl:radioOption>
        </mjl:radioGroup>    
      </mjl:dt>     
      
      <div class="lastSprayed">
        <mjl:dt attribute="lastSprayed">
          <mjl:input type="text" param="lastSprayed" id="lastSprayed" size="2" maxlength="2"/>
        </mjl:dt>
      </div>

      <mjl:dt attribute="nets">
        <c:if test="${!item.hasHouseholdNets}">
          <mjl:input type="text" param="nets" />
        </c:if>
        <c:if test="${item.hasHouseholdNets}">
          ${item.nets}
          <mjl:input type="hidden" param="nets" value="${item.nets}" />
        </c:if>      
      </mjl:dt>
      
    </mjl:component>
  
<%String[] types_to_load = {PropertyDTO.CLASS};%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var triggers = new Array('hasBeenSprayed.NO', 'hasBeenSprayed.DONT_KNOW');
    
    MDSS.ElementHandler.setupBooleanHandler('hasWindows.positive', 'hasWindows.negative', MDSS.HiddenInputElement.toArray(['windowType']));
    MDSS.ElementHandler.setupBooleanHandler('hasBeenSprayed.YES', triggers, MDSS.HiddenInputElement.toArray(['lastSprayed']));    
  
    //**********************************************************
    // SETUP UNIQUE ID GENERATOR
    //**********************************************************      
    YAHOO.util.Event.on('getUniqueId', 'click', function(e, obj){
      var request = new MDSS.Request({
        onSend: function(){},
        onComplete: function(){},
        onSuccess : function(result){
          document.getElementById('householdId').value = result;
        }
      });
      Mojo.$.dss.vector.solutions.Property.getNextId(request);
    });

    //**********************************************************
    // SETUP MO BROWSERS
    //**********************************************************    
    var attributes = [
      {attributeName:'wall'},
      {attributeName:'roof'},
      {attributeName:'windowType'}
    ];
  
    new MDSS.GenericOntologyBrowser("<%=HouseholdViewDTO.CLASS%>", attributes);
  })
})();
</script>