<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />
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
          <th><fmt:message key="Amount" /></th>
        </tr>
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <c:choose>
              <c:when test="${current.child.isAbstract}">
                <td colspan="2">${current.child.displayLabel}</td>
              </c:when>
              <c:otherwise>
                <td style="padding-left:2em">${current.child.displayLabel}</td>
                <td>
                  <mjl:input type="text" param="amount" />
                  <mjl:messages attribute="amount">
                    <mjl:message />
                  </mjl:messages>
                </td>
              </c:otherwise>
            </c:choose>
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
      <mjl:select param="freeProvider" items="${freeProvider}" var="current" valueAttribute="id" classes="freeProvider" includeBlank="true">
        <mjl:option>
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </mjl:dt>
  </div>
  <div class="boughtProvider">
    <mjl:dt attribute="boughtProvider">
      <mjl:select param="boughtProvider" items="${boughtProvider}" var="current" valueAttribute="id" classes="boughtProvider" includeBlank="true">
        <mjl:option>
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </mjl:dt>
  </div>
  <mjl:dt attribute="washed">
    <mjl:select param="washed" items="${washed}" var="current" valueAttribute="enumName" id="washed">
      <mjl:option selected="${mjl:contains(item.washedEnumNames, current.enumName) ? 'selected' : 'false'}" id="washed.${current.enumName}">
        ${item.washedMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <div class="washFrequency">
    <mjl:dt attribute="knowWashFrequency">
      <mjl:boolean param="knowWashFrequency" id="knowWashFrequency" classes="washFrequency" />
    </mjl:dt>
    <div class="knownFrequency">
      <mjl:dt attribute="washInterval">
        <mjl:select param="washInterval" items="${washInterval}" var="current" valueAttribute="enumName" includeBlank="true" classes="washFrequency knownFrequency">
          <mjl:option selected="${mjl:contains(item.washIntervalEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.washIntervalMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
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
      <mjl:select param="retreatmentPeriod" items="${retreatmentPeriod}" var="current" valueAttribute="id" classes="retreatmentPeriod" includeBlank="true">
        <mjl:option>
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </mjl:dt>
  </div>
</mjl:component>

<script type="text/javascript" defer="defer">
<!--
// Setup the option fields for batch number
MDSS.ElementHandler.setupBooleanHandler('netsObtained.positive', 'netsObtained.negative', 'freeProvider');
MDSS.ElementHandler.setupBooleanHandler('netsObtained.negative', 'netsObtained.positive', 'boughtProvider');
MDSS.ElementHandler.setupBooleanHandler('retreated.positive', 'retreated.negative', 'retreatmentPeriod');
MDSS.ElementHandler.setupBooleanHandler('usedEveryNight.negative', 'usedEveryNight.positive', 'nonUse');
MDSS.ElementHandler.setupSelectHandler('washed.YES', new Array('washed', 'washed.NO', 'washed.DONT_KNOW'), 'washFrequency');
MDSS.ElementHandler.setupBooleanHandler('knowWashFrequency.positive', 'knowWashFrequency.negative', 'knownFrequency');
//-->
</script>

