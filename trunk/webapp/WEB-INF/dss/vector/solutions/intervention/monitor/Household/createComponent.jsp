<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinelSiteDTO"%>
<c:set var="page_title" value="Create_Household"  scope="request"/>
<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<script type="text/javascript">

  YAHOO.util.Event.onDOMReady(function(){

    function selectHandler(geoId, entityId, selected)
    {
      var geoId = document.getElementById(geoId);
      var geoEntityId = document.getElementById(entityId);

      if(selected != null)
      {
        geoId.value = selected.getGeoId();
        geoEntityId.value = selected.getGeoEntityId();
      }
      else
      {
        geoId.value = '';
        geoEntityId.value = '';
      }
    }

    var selectSearch = new MDSS.SingleSelectSearch();
    selectSearch.setFilter('');

    var roofSearch = new YAHOO.util.Element("roofSearch");
    var wallSearch = new YAHOO.util.Element("wallSearch");


    roofSearch.on("click", function()
    {
      var curried = MDSS.util.curry(selectHandler, "roofGeoId", "roofId");
      selectSearch.setSelectHandler(curried);
      selectSearch.setTreeSelectHandler(curried);

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });

    wallSearch.on("click", function()
    {
      var curried = MDSS.util.curry(selectHandler, "wallGeoId", "wallId");
      selectSearch.setSelectHandler(curried);
      selectSearch.setTreeSelectHandler(curried);

      if(selectSearch.isInitialized())
      {
        selectSearch.show();
      }
      else
      {
        selectSearch.render();
      }
    });
  }, null, true);


</script>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="Household.form.id" method="POST">
  <dl>
    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="surveyPoint" value="${item.surveyPoint.id}" />
      <mjl:dt attribute="householdName">
        <mjl:input type="text" param="householdName" />

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
      <mjl:dt attribute="people">
        <mjl:input type="text" param="people" />

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
      <mjl:dt attribute="rooms">
        <mjl:input type="text" param="rooms" />

      </mjl:dt>
      <mjl:dt attribute="sleptUnderNets">
        <mjl:input type="text" param="sleptUnderNets" />

      </mjl:dt>
      <mjl:dt attribute="urban">
        <mjl:boolean param="urban" trueLabel="urban" falseLabel="rural" />

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
    </mjl:component>

    <dt><label><fmt:message key="Nets" /> </label></dt>
    <dd>
    <table class="displayTable">
      <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
        <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
          <c:choose>
            <c:when test="${current.child.isAbstract}">
              <td colspan="2">${current.child.displayLabel}</td>
            </c:when>
            <c:otherwise>
              <td>${current.child.displayLabel}</td>
              <td><mjl:input type="text" param="amount" /> <mjl:messages attribute="amount">
                <mjl:message />
              </mjl:messages></td>
            </c:otherwise>
          </c:choose>
        </tr>
      </mjl:components>
    </table>
    </dd>
  </dl>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.HouseholdController.create.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.create.button" />
</mjl:form>

<div id="cal1Container" class="yui-skin-sam"></div>
<%
    //out.flush();
%>