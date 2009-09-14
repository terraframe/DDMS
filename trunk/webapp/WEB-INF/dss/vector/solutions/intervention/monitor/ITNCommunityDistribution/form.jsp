<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
  <mjl:dt attribute="hasBatchNumber" >
    <mjl:boolean param="hasBatchNumber" id="hasBatchNumber"/>
  </mjl:dt>
  <div class = "batchNumber" >  
    <mjl:dt attribute="batchNumber">
      <mjl:input param="batchNumber" type="text" classes="batchNumber"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="entryType">
    <mjl:boolean param="entryType" id="entryType" />
  </mjl:dt>  
  <div class="household">
    <mjl:dt attribute="householdName">
      <mjl:input param="householdName" type="text" classes="household"/>
    </mjl:dt>
    <mjl:dt attribute="householdSurname">
      <mjl:input param="householdSurname" type="text" classes="household"/>
    </mjl:dt>  
    <mjl:dt attribute="householdAddress">
      <mjl:input id="householdAddress" param="householdAddress" type="text" classes="geoInput household" />
    </mjl:dt>
    <mjl:dt attribute="residents">
      <mjl:input param="residents" type="text" classes="household"/>
    </mjl:dt>
  </div>
  <div class="distributionLocation">    
    <mjl:dt attribute="distributionLocation">
      <mjl:input id="distributionLocation" param="distributionLocation" type="text" classes="geoInput distributionLocation" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="sold">
    <mjl:boolean param="sold" id="sold"/>
  </mjl:dt>
  <div class="currencyReceived">
    <mjl:dt attribute="currencyReceived">
      <mjl:input param="currencyReceived" type="text" classes="currencyReceived"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="retrieved">
    <mjl:boolean param="retrieved" id="retrieved"/>
  </mjl:dt>
  <div class="numberRetrieved">  
    <mjl:dt attribute="numberRetrieved">
      <mjl:input param="numberRetrieved" type="text" classes="numberRetrieved" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="pretreated">
    <mjl:boolean param="pretreated" />
  </mjl:dt>
</mjl:component>

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

<script type="text/javascript" defer="defer">
<!--
// Setup the option fields for batch number
MDSS.BooleanElementHandler.setupHandler('hasBatchNumber.positive', 'hasBatchNumber.negative', 'batchNumber');
MDSS.BooleanElementHandler.setupHandler('entryType.positive', 'entryType.negative', 'household');
MDSS.BooleanElementHandler.setupHandler('entryType.negative', 'entryType.positive', 'distributionLocation');
MDSS.BooleanElementHandler.setupHandler('sold.positive', 'sold.negative', 'currencyReceived');
MDSS.BooleanElementHandler.setupHandler('retrieved.positive', 'retrieved.negative', 'numberRetrieved');
//-->
</script>
