<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_ITN_Data"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.ITNData.form.name" id="dss.vector.solutions.intervention.monitor.ITNData.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="geoId">
        ${item.geoId}
      </mjl:dt>
      <mjl:dt attribute="periodType">
        ${item.periodTypeEnumNames[0]}
      </mjl:dt>
      <mjl:dt attribute="period">
        ${item.period}
      </mjl:dt>
      <mjl:dt attribute="periodYear">
        ${item.periodYear}
      </mjl:dt>    
      <mjl:dt attribute="batchNumber">
        ${item.batchNumber}
      </mjl:dt>
      <mjl:dt attribute="receivedForTargetGroups">      
        ${item.batchNumber}
      </mjl:dt>      
      <mjl:dt attribute="receivedForCommunityResponse">      
        ${item.batchNumber}
      </mjl:dt>      
      <mjl:dt attribute="numberDistributed">      
        ${item.batchNumber}
      </mjl:dt>     
      <mjl:dt attribute="numberSold">      
        ${item.batchNumber}
      </mjl:dt>      
      <mjl:dt attribute="currencyReceived">      
        ${item.batchNumber}
      </mjl:dt>
      
    </mjl:component>    
    <c:if test="${item.isDisplayServicesReadable}">
      <dt>
      
      </dt>
      <dd>
        <table class="displayTable">
          <tr> 
            <th><fmt:message key="service_received"/></th>
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
            <th><fmt:message key="target_groups"/></th>
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

    <c:if test="${item.isDisplayTargetGroupsReadable}">
      <dt>
    
      </dt>
      <dd>
        <table class="displayTable">
          <tr>
            <th><fmt:message key="total_itn_distributed" /></th>
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
                    ${current.amount}
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
    
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.ITNDataController.edit.mojo" name="dss.vector.solutions.intervention.monitor.ITNData.form.edit.button" />
  </dl>
</mjl:form>