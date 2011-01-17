<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ITNCommunityDistribution" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.id" name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.name" method="POST">
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
      <mjl:dt attribute="agentFirstName">
        ${item.agentFirstName}
      </mjl:dt>
      <mjl:dt attribute="agentSurname">
        ${item.agentSurname}
      </mjl:dt>
      <mjl:dt attribute="itnsReceived">
        ${item.itnsReceived}
      </mjl:dt>
      <mjl:dt attribute="hasBatchNumber">
        ${item.hasBatchNumber ? item.hasBatchNumberMd.positiveDisplayLabel : item.hasBatchNumberMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="batchNumber">
        ${item.batchNumber}
      </mjl:dt>
      <mjl:dt attribute="entryType">
        ${item.entryType ? item.entryTypeMd.positiveDisplayLabel : item.entryTypeMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="householdName">
        ${item.householdName}
      </mjl:dt>
      <mjl:dt attribute="householdSurname">
        ${item.householdSurname}
      </mjl:dt>
      <mjl:dt attribute="householdAddress">
        ${householdAddress != null ? householdAddress.displayString : ''}
      </mjl:dt>                  
      <mjl:dt attribute="residents">
        ${item.residents}
      </mjl:dt>
      <mjl:dt attribute="distributionLocation">
        ${distributionLocation != null ? distributionLocation.displayString : ''}
      </mjl:dt>      
      <c:if test="${item.isDisplayTargetGroupsReadable}">
        <dt>      
        </dt>
        <dd>
          <table class="displayTable">
            <tr> 
              <th> ${item.displayTargetGroupsMd.displayLabel} </th>
              <th><mdss:localize key="Amount"/></th>
            </tr>      
            <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
              <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
                <td>
                  ${current.child.displayLabel}
                </td>
                <td class="integerColumn">
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
              <th><mdss:localize key="Amount"/></th>
            </tr>      
            <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
              <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
                <td>
                  ${current.child.displayLabel}
                </td>
                <td class="integerColumn">
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
            
      <mjl:dt attribute="sold">
        ${item.sold ? item.soldMd.positiveDisplayLabel : item.soldMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="currencyReceived">
        ${item.currencyReceived}
      </mjl:dt>      
      <mjl:dt attribute="retrieved">
        ${item.retrieved ? item.retrievedMd.positiveDisplayLabel : item.retrievedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="numberRetrieved">
        ${item.numberRetrieved}
      </mjl:dt>
      <mjl:dt attribute="pretreated">
        ${item.pretreated ? item.pretreatedMd.positiveDisplayLabel : item.pretreatedMd.negativeDisplayLabel}
      </mjl:dt>      
    </mjl:component>    
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.intervention.monitor.ITNCommunityDistribution.viewAll.link" action="dss.vector.solutions.intervention.monitor.ITNCommunityDistributionController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
