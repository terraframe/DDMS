<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ITNHouseholdSurvey" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.id" name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="startDate">
        <span class="formatDate">
          ${item.startDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">
          ${item.endDate}
        </span>
      </mjl:dt>
      <mjl:dt attribute="surveyLocation">
        ${item.surveyLocation}
      </mjl:dt>      
      <mjl:dt attribute="agentFirstName">
        ${item.agentFirstName}
      </mjl:dt>
      <mjl:dt attribute="agentSurname">
        ${item.agentSurname}
      </mjl:dt>
      <mjl:dt attribute="residents">
        ${item.residents}
      </mjl:dt>
      <mjl:dt attribute="pregnantResidents">
        ${item.pregnantResidents}
      </mjl:dt>      
      <mjl:dt attribute="childResidents">
        ${item.childResidents}
      </mjl:dt>
      <mjl:dt attribute="questionnaireNumber">
        ${item.questionnaireNumber}
      </mjl:dt>      
      <mjl:dt attribute="itns">
        ${item.itns}
      </mjl:dt>
      <mjl:dt attribute="damagedItns">
        ${item.damagedItns}
      </mjl:dt>      
      <mjl:dt attribute="hangingItns">
        ${item.hangingItns}
      </mjl:dt>      
      <mjl:dt attribute="otherItns">
        ${item.otherItns}
      </mjl:dt>
      <mjl:dt attribute="monthReceived">
        ${item.monthReceived}
      </mjl:dt>      
      <mjl:dt attribute="yearReceived">
        ${item.yearReceived}
      </mjl:dt>      
      <mjl:dt attribute="usedItns">
        ${item.usedItns}
      </mjl:dt>      
      <mjl:dt attribute="usedEveryNight">
        ${item.usedEveryNight ? item.usedEveryNightMd.positiveDisplayLabel : item.usedEveryNightMd.negativeDisplayLabel}
      </mjl:dt>      
      
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
                  ${current.amount}
                  <mjl:messages attribute="amount">
                    <mjl:message />
                  </mjl:messages>
                </td>
              </tr>
            </mjl:components>
          </table>
        </dd>
      </c:if>
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
                  ${current.amount}
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
                  ${current.amount}
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
        ${item.netsObtained ? item.netsObtainedMd.positiveDisplayLabel : item.netsObtainedMd.negativeDisplayLabel}
      </mjl:dt>      
      <mjl:dt attribute="freeProvider">
        <c:if test="${freeProvider != null}">
          ${freeProvider.displayLabel}
        </c:if>
      </mjl:dt>      
      <mjl:dt attribute="boughtProvider">
        <c:if test="${boughtProvider != null}">
          ${boughtProvider.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="washed">
        <c:if test="${washed != null}">
          ${washed.displayLabel}
        </c:if>
      </mjl:dt>      
      <mjl:dt attribute="washFrequency">
        ${item.washFrequency}
      </mjl:dt>      
      <mjl:dt attribute="washInterval">
        <c:if test="${washInterval != null}">
          ${washInterval.displayLabel}
        </c:if>
      </mjl:dt>      
      <mjl:dt attribute="retreated">
        ${item.retreated ? item.retreatedMd.positiveDisplayLabel : item.retreatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="retreatmentPeriod">
        <c:if test="${retreatmentPeriod != null}">
          ${retreatmentPeriod.displayLabel}
        </c:if>
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.ITNHouseholdSurvey.viewAll.link" action="dss.vector.solutions.intervention.monitor.ITNHouseholdSurveyController.viewAll.mojo">
  <fmt:message key="View_All" />
</mjl:commandLink>
