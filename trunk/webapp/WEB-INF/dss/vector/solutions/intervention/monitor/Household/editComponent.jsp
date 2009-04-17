<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.terraframe.mojo.web.json.JSONController"%>

<%@page import="dss.vector.solutions.geo.GeoEntityTreeController"%>
<%@page import="dss.vector.solutions.geo.generated.SentinalSiteDTO"%>
<%@page import="dss.vector.solutions.geo.generated.NonSentinalSiteDTO"%>
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

    var roofSearch = new YAHOO.util.Element("roofSearch");
    var wallSearch = new YAHOO.util.Element("wallSearch");

    roofSearch.on("click", function()
    {
      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var curried = MDSS.util.curry(selectHandler, "roofGeoId", "roofId");
        MDSS.SelectSearch.initialize(curried, curried, "");
      }
    });

    wallSearch.on("click", function()
    {
      if(MDSS.SelectSearch.isInitialized())
      {
        MDSS.SelectSearch.show();
      }
      else
      {
        var curried = MDSS.util.curry(selectHandler, "wallGeoId", "wallId");
        MDSS.SelectSearch.initialize(curried, curried, "");
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
    <mjl:input type="hidden" param="surveyPoint" value="${item.surveyPoint.id}"/>
      <dt>
        <label>
          ${item.householdNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="householdName" />
        <mjl:messages attribute="householdName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lastSprayedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lastSprayed" />
        <mjl:messages attribute="lastSprayed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.netsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="nets" />
        <mjl:messages attribute="nets">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.netsUsedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="netsUsed" />
        <mjl:messages attribute="netsUsed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.peopleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="people" />
        <mjl:messages attribute="people">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roofMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input id="roofGeoId" param="blank" type="text" /><span id="roofSearch" class="clickable"><img src="./imgs/icons/world.png"/></span>
        <mjl:input id="roofId" param="roof" type="hidden" />
        <mjl:messages attribute="roof">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roofInfoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="roofInfo" />
        <mjl:messages attribute="roofInfo">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roomsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="rooms" />
        <mjl:messages attribute="rooms">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sleptUnderNetsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sleptUnderNets" />
        <mjl:messages attribute="sleptUnderNets">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.urbanMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="urban" trueLabel="urban" falseLabel="rural" />
        <mjl:messages attribute="urban">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.wallMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input id="wallGeoId" param="blank" type="text" readonly="true" /><span id="wallSearch" class="clickable"><img src="./imgs/icons/world.png"/></span>
        <mjl:input id="wallId" param="wall" type="hidden" />
        <mjl:messages attribute="wall">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.wallInfoMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="wallInfo" />
        <mjl:messages attribute="wallInfo">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.hasWindowsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="hasWindows" trueLabel="Yes" falseLabel="No"/>
        <mjl:messages attribute="hasWindows">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.windowTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${windowType}" param="windowType">
          <mjl:option selected="${mjl:contains(item.windowTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.windowTypeMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="windowType">
          <mjl:message />
        </mjl:messages>
      </dd>
      </mjl:component>

      <dt>
        <label><fmt:message key="Nets"/> </label>
      </dt>
      <dd>
        <table class="displayTable">
          <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
            <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
              <c:choose>
                <c:when test="${current.child.isAbstract}">
                  <td colspan="2">
                    ${current.child.displayLabel}
                  </td>
                </c:when>
              <c:otherwise>
                <td>
                  ${current.child.displayLabel}
                </td>
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
    </dl>
    <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.HouseholdController.update.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.HouseholdController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.HouseholdController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.cancel.button" />
</mjl:form>