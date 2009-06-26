<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.PropertyDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="surveyPoint" value="${item.surveyPoint.id}" />

      <mjl:dt attribute="householdName">
        <mjl:input type="text" param="householdName" id="householdId"/><a href="#" id="getUniqueId"><fmt:message key="Get_Unique_Id"/></a>
      </mjl:dt>

      <mjl:dt attribute="urban">
        <mjl:boolean param="urban" />
      </mjl:dt>

      <mjl:dt attribute="people">
        <mjl:input type="text" param="people" />
      </mjl:dt>

      <mjl:dt attribute="wall">
       <mjl:select var="current" varStatus="status" valueAttribute="wallId" items="${walls}" param="wall">
          <c:if test="${status.index!=0 && !current.hasParent && walls[status.index-1].hasParent}">
              </optgroup>
          </c:if>
          <c:choose>
            <c:when test="${current.hasChildren}">
              <optgroup label="${current.displayLabel}">
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${current.displayLabel}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </mjl:dt>

      <mjl:dt attribute="wallInfo">
        <mjl:input type="text" param="wallInfo" />
      </mjl:dt>

      <mjl:dt attribute="roof">
       <mjl:select var="current" varStatus="status" valueAttribute="roofId" items="${roofs}" param="roof">
          <c:if test="${status.index!=0 && !current.hasParent && roofs[status.index-1].hasParent}">
              </optgroup>
          </c:if>
          <c:choose>
            <c:when test="${current.hasChildren}">
              <optgroup label="${current.displayLabel}">
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${current.displayLabel}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </mjl:dt>

      <mjl:dt attribute="roofInfo">
        <mjl:input type="text" param="roofInfo" />
      </mjl:dt>

      <mjl:dt attribute="hasWindows">
        <mjl:boolean param="hasWindows" trueLabel="Yes" falseLabel="No" />
      </mjl:dt>

      <mjl:dt attribute="windowType">
        <mjl:select var="current" valueAttribute="enumName" items="${windowType}" param="windowType" includeBlank="true">
          <mjl:option selected="${mjl:contains(item.windowTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.windowTypeMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
      </mjl:dt>

      <mjl:dt attribute="rooms">
        <mjl:input type="text" param="rooms" />
      </mjl:dt>

      <mjl:dt attribute="lastSprayed">
        <mjl:input type="text" param="lastSprayed" />
      </mjl:dt>

      <mjl:dt attribute="nets">
        <mjl:input type="text" param="nets" />

      </mjl:dt>
      <mjl:dt attribute="netsUsed">
        <mjl:input type="text" param="netsUsed" />
      </mjl:dt>

      <mjl:dt attribute="sleptUnderNets">
        <mjl:input type="text" param="sleptUnderNets" />
      </mjl:dt>

    </mjl:component>

    <dt></dt>
    <dd>
      <table class="displayTable">
        <tr>
          <th><fmt:message key="Nets" /></th>
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

<%String[] types_to_load = {PropertyDTO.CLASS};%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>

<script type="text/javascript">
YAHOO.util.Event.on('getUniqueId', 'click', function(e, obj){
var request = new MDSS.Request({
    onSend: function(){},
    onComplete: function(){},
    onSuccess : function(result){
    document.getElementById('householdId').value = result;
  }
});
Mojo.$.dss.vector.solutions.Property.getNextId(request);
});
</script>