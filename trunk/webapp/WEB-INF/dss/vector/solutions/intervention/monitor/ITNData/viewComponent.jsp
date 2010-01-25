<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_ITN_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.ITNData.form.name" id="dss.vector.solutions.intervention.monitor.ITNData.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoId">
        ${entity.displayString}
      </mjl:dt>
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>      
      </mjl:dt>      
<!-- 
      <mjl:dt attribute="periodType">
        <ul>
          <c:forEach var="enumName" items="${item.periodTypeEnumNames}">
            <li>${item.periodTypeMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>      
      </mjl:dt>
      <mjl:dt attribute="period">
        ${item.period}
      </mjl:dt>
      <mjl:dt attribute="periodYear">
        ${item.periodYear}
      </mjl:dt>    
 -->      
      <mjl:dt attribute="batchNumber">
        ${item.batchNumber}
      </mjl:dt>
      <mjl:dt attribute="receivedForTargetGroups">      
        ${item.receivedForTargetGroups}
      </mjl:dt>      
      <mjl:dt attribute="receivedForCommunityResponse">      
        ${item.receivedForCommunityResponse}
      </mjl:dt>      
      <mjl:dt attribute="numberDistributed">      
        ${item.numberDistributed}
      </mjl:dt>     
      <mjl:dt attribute="numberSold">      
        ${item.numberSold}
      </mjl:dt>      
      <mjl:dt attribute="currencyReceived">      
        ${item.currencyReceived}
      </mjl:dt>
      
    </mjl:component>    
    <c:if test="${item.isDisplayServicesReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th>${item.displayServicesMd.displayLabel}</th>
            <th><fmt:message key="Amount"/></th>
          </tr>      
          <mjl:components items="${services}" param="services" var="current" varStatus="status">
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
    
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.ITNDataController.edit.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.edit.button" />
    <mjl:command value="Create_New_ITN_Data_Button" action="dss.vector.solutions.intervention.monitor.ITNDataController.search.mojo" name="ITNDataController.search" />
    
  </dl>
</mjl:form>