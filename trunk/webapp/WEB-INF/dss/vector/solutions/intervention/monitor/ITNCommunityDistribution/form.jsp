<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />  
  <mjl:dt classes="DatePick NoFuture" attribute="startDate" type="text" />
  <mjl:dt classes="DatePick NoFuture" attribute="endDate" type="text" />
  <mjl:dt attribute="agentFirstName">
    <mjl:input param="agentFirstName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="agentSurname">
    <mjl:input param="agentSurname" type="text" />
  </mjl:dt>
  <mjl:dt attribute="itnsReceived">
    <mjl:input param="itnsReceived" type="text" />
  </mjl:dt>  
  <mjl:dt attribute="hasBatchNumber" >
    <mjl:boolean param="hasBatchNumber" id="hasBatchNumber"/>
  </mjl:dt>
  <div class = "batchNumber" >  
    <mjl:dt attribute="batchNumber">
      <mjl:input param="batchNumber" type="text" id="batchNumber"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="entryType">
    <mjl:boolean param="entryType" id="entryType" />
  </mjl:dt>  
  <div class="householdName">
    <mjl:dt attribute="householdName">
      <mjl:input param="householdName" type="text" id="householdName"/>
    </mjl:dt>
  </div>
  <div class="householdSurname">
    <mjl:dt attribute="householdSurname">
      <mjl:input param="householdSurname" type="text" id="householdSurname"/>
    </mjl:dt>  
  </div>
  <div class="householdAddress">
    <mjl:dt attribute="householdAddress">
      <mdss:geo param="householdAddress" concrete="false" />
    </mjl:dt>
  </div>
  <div class="residents">
    <mjl:dt attribute="residents">
      <mjl:input param="residents" type="text" id="residents"/>
    </mjl:dt>
  </div>
  
  <div class="distributionLocation">    
    <mjl:dt attribute="distributionLocation">
      <mdss:geo param="distributionLocation" concrete="false" />    
    </mjl:dt>
  </div>
  
  <c:if test="${item.isDisplayTargetGroupsReadable}">
    <dt>    
    </dt>
    <dd>
      <table class="displayTable">
        <tr> 
          <th>${item.displayTargetGroupsMd.displayLabel}</th>
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${targetGroups}" param="targetGroups" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
          <th><mdss:localize key="Amount"/></th>
        </tr>      
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <td>
              ${current.child.displayLabel}
            </td>
            <td class="integerColumn">
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
  
  <mjl:dt attribute="sold">
    <mjl:boolean param="sold" id="sold"/>
  </mjl:dt>
  <div class="currencyReceived">
    <mjl:dt attribute="currencyReceived">
      <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" var="formattedCurrencyReceived" value="${item.currencyReceived}" />
      <mjl:input param="currencyReceived" type="text" id="currencyReceived" value="${formattedCurrencyReceived}"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="retrieved">
    <mjl:boolean param="retrieved" id="retrieved"/>
  </mjl:dt>
  <div class="numberRetrieved">  
    <mjl:dt attribute="numberRetrieved">
      <mjl:input param="numberRetrieved" type="text" id="numberRetrieved" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="pretreated">
    <mjl:boolean param="pretreated" />
  </mjl:dt>
</mjl:component>


<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    MDSS.ElementHandler.setupBooleanHandler('hasBatchNumber.positive', 'hasBatchNumber.negative', MDSS.HiddenInputElement.toArray(['batchNumber']));
    MDSS.ElementHandler.setupBooleanHandler('entryType.positive', 'entryType.negative', MDSS.HiddenInputElement.toArray(['householdName','householdSurname','householdAddress','residents']));
    MDSS.ElementHandler.setupBooleanHandler('entryType.negative', 'entryType.positive', MDSS.HiddenInputElement.toArray(['distributionLocation']));
    MDSS.ElementHandler.setupBooleanHandler('sold.positive', 'sold.negative', MDSS.HiddenInputElement.toArray(['currencyReceived']));
    MDSS.ElementHandler.setupBooleanHandler('retrieved.positive', 'retrieved.negative', MDSS.HiddenInputElement.toArray(['numberRetrieved']));
  })
})();
</script>
