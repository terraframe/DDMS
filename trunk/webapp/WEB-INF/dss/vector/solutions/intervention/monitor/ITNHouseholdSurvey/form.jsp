<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@page import="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyViewDTO"%><jsp:include page="/WEB-INF/selectSearch.jsp" />



<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" /> 
  <mjl:dt classes="DatePick" attribute="startDate" type="text" />
  <mjl:dt classes="DatePick" attribute="endDate" type="text" />
  <mjl:dt attribute="surveyLocation">
    <mjl:input classes="geoInput" id="geoIdEl" param="surveyLocation" type="text" />
  </mjl:dt>  
  <mjl:dt attribute="agentFirstName">
    <mjl:input param="agentFirstName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="agentSurname">
    <mjl:input param="agentSurname" type="text" />
  </mjl:dt>
  <mjl:dt attribute="questionnaireNumber">
    <mjl:input param="questionnaireNumber" type="text" />
  </mjl:dt>
  <mjl:dt attribute="residents">
    <mjl:input param="residents" type="text" />
  </mjl:dt>
  <mjl:dt attribute="pregnantResidents">
    <mjl:input param="pregnantResidents" type="text" />
  </mjl:dt>
  <mjl:dt attribute="childResidents">
    <mjl:input param="childResidents" type="text" />
  </mjl:dt>
  <mjl:dt attribute="itns">
    <mjl:input param="itns" type="text" />
  </mjl:dt>
  <mjl:dt attribute="damagedItns">
    <mjl:input param="damagedItns" type="text" />
  </mjl:dt>
  <mjl:dt attribute="hangingItns">
    <mjl:input param="hangingItns" type="text" />
  </mjl:dt>
  <mjl:dt attribute="otherItns">
    <mjl:input param="otherItns" type="text" />
  </mjl:dt>
  <mjl:dt attribute="monthReceived">
    <mjl:input param="monthReceived" type="text" size="2" maxlength="2" />
  </mjl:dt>
  <mjl:dt attribute="yearReceived">
    <mjl:input param="yearReceived" type="text" size="4" maxlength="4" />
  </mjl:dt>
  <mjl:dt attribute="usedItns">
    <mjl:input param="usedItns" type="text" />
  </mjl:dt>
  <mjl:dt attribute="usedEveryNight">
    <mjl:boolean param="usedEveryNight" id="usedEveryNight" />
  </mjl:dt>
  <div class="nonUse">
    <c:if test="${item.isDisplayNonUseReasonsReadable}">
      <dt>    
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th>${item.displayNonUseReasonsMd.displayLabel}</th>
            <th><fmt:message key="Amount"/></th>
          </tr>      
          <mjl:components items="${reasons}" param="reasons" var="current" varStatus="status">
            <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
              <td>
                ${current.child.displayLabel}
              </td>
              <td>
                <mjl:input type="text" param="amount" classes="nonUse"/>
                <mjl:messages attribute="amount">
                  <mjl:message />
                </mjl:messages>
              </td>
            </tr>
          </mjl:components>
        </table>
      </dd>
    </c:if>
  </div>
  <c:if test="${item.isDisplayTargetGroupsReadable}">
    <dt>    
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayTargetGroupsMd.displayLabel}</th>
          <th><fmt:message key="Amount"/></th>
        </tr>      
        <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>
  </c:if>

  <c:if test="${item.isDisplayNetsReadable}">
    <dt>    
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayNetsMd.displayLabel}</th>
          <th><fmt:message key="Amount"/></th>
        </tr>      
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td>
              <mjl:input type="text" param="amount" />
              <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages>
            </td>
          </tr>
        </mjl:components>
      </table>
    </dd>
  </c:if>  
  <mjl:dt attribute="netsObtained">
    <mjl:boolean param="netsObtained" id="netsObtained" />
  </mjl:dt>
  <div class="freeProvider">
    <mjl:dt attribute="freeProvider">
      <span class="clickable browserLauncher" id="freeProviderBtn"> <fmt:message key="Browser"/></span>
      <div id="freeProviderDisplay" class="ontologyDisplay freeProvider">
        <c:choose>
          <c:when test="${freeProvider != null}">
            ${freeProvider.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input type="hidden" param="freeProvider" id="freeProvider" value="${freeProvider != null ? freeProvider.id : ''}" classes="freeProvider" />
    </mjl:dt>
  </div>
  <div class="boughtProvider">
    <mjl:dt attribute="boughtProvider">
      <span class="clickable browserLauncher" id="boughtProviderBtn"> <fmt:message key="Browser"/></span>
      <div id="boughtProviderDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${boughtProvider != null}">
            ${boughtProvider.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input type="hidden" param="boughtProvider" id="boughtProvider" value="${boughtProvider != null ? boughtProvider.id : ''}" classes="boughtProvider" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="washed">
    <span class="clickable browserLauncher" id="washedBtn"> <fmt:message key="Browser"/></span>
    <div id="washedDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${washed != null}">
            ${washed.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
    </div>
    <mjl:input type="hidden" param="washed" id="washed" value="${washed != null ? washed.id : ''}" />
  </mjl:dt>
  <div class="washFrequency">
    <mjl:dt attribute="knowWashFrequency">
      <mjl:boolean param="knowWashFrequency" id="knowWashFrequency" classes="washFrequency" />
    </mjl:dt>
    <div class="knownFrequency">
      <mjl:dt attribute="washInterval">
        <span class="clickable browserLauncher" id="washIntervalBtn"> <fmt:message key="Browser"/></span>
        <div id="washIntervalDisplay" class="ontologyDisplay washFrequency knownFrequency">
        <c:choose>
          <c:when test="${washInterval != null}">
            ${washInterval.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
        </div>
        <mjl:input type="hidden" param="washInterval" id="washInterval" value="${washInterval != null ? washInterval.id : ''}" classes="washFrequency knownFrequency"  />
      </mjl:dt>
   
      <mjl:dt attribute="washFrequency">
        <mjl:input param="washFrequency" type="text" classes="washFrequency knownFrequency"/>
      </mjl:dt>
    </div>
  </div>
  <mjl:dt attribute="retreated">
    <mjl:boolean param="retreated" id="retreated" />
  </mjl:dt>
  <div class="retreatmentPeriod">
    <mjl:dt attribute="retreatmentPeriod">
      <span class="clickable browserLauncher" id="retreatmentPeriodBtn"> <fmt:message key="Browser"/></span>
      <div id="retreatmentPeriodDisplay" class="ontologyDisplay retreatmentPeriod">
        <c:choose>
          <c:when test="${retreatmentPeriod != null}">
            ${retreatmentPeriod.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input type="hidden" param="retreatmentPeriod" id="retreatmentPeriod" value="${retreatmentPeriod != null ? retreatmentPeriod.id : ''}" classes="retreatmentPeriod" />
    </mjl:dt>
  </div>
</mjl:component>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var attrs = [
      {attributeName:'freeProvider'},
      {attributeName:'boughtProvider'},
      {attributeName:'washed'},
      {attributeName:'washInterval'},
      {attributeName:'retreatmentPeriod'}
    ];

    new MDSS.GenericOntologyBrowser("<%=ITNHouseholdSurveyViewDTO.CLASS%>", attrs);

    // Setup the option fields for batch number
    MDSS.ElementHandler.setupBooleanHandler('netsObtained.positive', 'netsObtained.negative', 'freeProvider');
    MDSS.ElementHandler.setupBooleanHandler('netsObtained.negative', 'netsObtained.positive', 'boughtProvider');
    MDSS.ElementHandler.setupBooleanHandler('retreated.positive', 'retreated.negative', 'retreatmentPeriod');
    MDSS.ElementHandler.setupBooleanHandler('usedEveryNight.negative', 'usedEveryNight.positive', 'nonUse');
    MDSS.ElementHandler.setupSelectHandler('washed.YES', new Array('washed', 'washed.NO', 'washed.DONT_KNOW'), 'washFrequency');
    MDSS.ElementHandler.setupBooleanHandler('knowWashFrequency.positive', 'knowWashFrequency.negative', 'knownFrequency');
  })
})();
</script>
