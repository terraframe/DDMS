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

<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>


<mjl:component item="${item}" param="dto">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="geoId">
    <mdss:geo param="geoId" concrete="false" universals="${entityUniversals}" >
      <mdss:filter universal="" id="all" checked="true">&nbsp;<mdss:localize key="All"/>  &nbsp;&nbsp;&nbsp;</mdss:filter>
      <mdss:filter universal="${SentinelSite}" id="sentinel_site">&nbsp;<mdss:localize key="Sentinel_Site"/> &nbsp;&nbsp;&nbsp; <br /></mdss:filter>
    </mdss:geo>
  </mjl:dt>
  <mjl:dt attribute="surveyDate">
    <mjl:input param="surveyDate" type="text" classes="DatePick NoFuture" id="surveyDate"/>
  </mjl:dt>
</mjl:component>