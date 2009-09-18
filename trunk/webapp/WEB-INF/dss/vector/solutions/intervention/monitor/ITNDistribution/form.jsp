<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="recipient" />
  <mjl:dt attribute="facility">
    <mjl:input value="${item.facility != null ? item.facility.geoId : ''}" type="text" param="facilityId" classes="geoInput" id="geoIdEl" />
    <mjl:input type="hidden" param="facility" id="geoEntityId" />
  </mjl:dt>
  <mjl:dt attribute="distributionDate" >
    <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
  </mjl:dt>
  <mjl:dt attribute="service">
    <mjl:select var="current" valueAttribute="id" items="${service}" param="service">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="batchNumber">
    <mjl:input type="text" param="batchNumber" />
  </mjl:dt>
  
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
            <mjl:input type="text" param="amount" />
            <mjl:messages attribute="amount">
              <mjl:message />
            </mjl:messages>
          </td>
        </tr>
      </mjl:components>
    </table>
  </dd>
  
  <mjl:dt attribute="net">
    <mjl:select var="current" valueAttribute="id" items="${net}" param="net">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="numberSold">
    <mjl:input type="text" param="numberSold" />
  </mjl:dt>
  <mjl:dt attribute="currencyReceived">
    <mjl:input type="text" param="currencyReceived" />
  </mjl:dt>
  <mjl:dt attribute="distributorName">
    <mjl:input type="text" param="distributorName" />
  </mjl:dt>
  <mjl:dt attribute="distributorSurname">
    <mjl:input type="text" param="distributorSurname" />
  </mjl:dt>
</mjl:component>
