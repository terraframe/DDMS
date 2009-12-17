<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
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
  
  <mjl:dt attribute="sold">
    <mjl:boolean param="sold" id="sold"/>
  </mjl:dt>
  <div class="currencyReceived">
    <mjl:dt attribute="currencyReceived">
      <mjl:input param="currencyReceived" type="text" id="currencyReceived"/>
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
