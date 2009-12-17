<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="com.terraframe.mojo.constants.ClientRequestIF"%>
<%@page import="com.terraframe.mojo.constants.ClientConstants"%>
<%@page import="dss.vector.solutions.geo.generated.SentinelSiteDTO"%>

<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="geoId">
    <mdss:geo param="geoId" concrete="false">
      <mdss:filter universal="" id="all" checked="true">&nbsp;<fmt:message key="All"/>  &nbsp;&nbsp;&nbsp;</mdss:filter>
      <mdss:filter universal="${SentinelSiteClass}" id="sentinel_site">&nbsp;<fmt:message key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp; <br /></mdss:filter>
    </mdss:geo>
  </mjl:dt>
  <mjl:dt attribute="surveyDate">
    <mjl:input param="surveyDate" type="text" classes="DatePick NoFuture" id="surveyDate"/>
  </mjl:dt>
</mjl:component>