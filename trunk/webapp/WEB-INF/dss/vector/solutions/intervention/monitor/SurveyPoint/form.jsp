<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <dt><fmt:message key="Filter"/></dt>
  <dd>
    <input type="radio" name="filterType" value="" checked="checked" />&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;
    <input type="radio" name="filterType" value="${SentinelSiteClass}" />&nbsp;<fmt:message key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp;
  </dd>
  <mjl:dt attribute="geoId">
    <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/>

  </mjl:dt>
  <mjl:dt attribute="surveyDate">
    <mjl:input param="surveyDate" type="text" classes="DatePick NoFuture" id="surveyDate"/>
</mjl:dt>
</mjl:component>