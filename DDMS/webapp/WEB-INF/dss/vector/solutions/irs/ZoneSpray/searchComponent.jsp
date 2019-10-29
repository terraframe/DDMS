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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.geo.GeoHierarchyDTO"%>
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<c:set var="page_title" value="Search_for_a_Zone_Spray"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<mjl:form name="search" method="POST" id ="searchZoneSpray">
  <dl>
    <dt> <mdss:localize key="Geo_Entity"/> </dt>
    <dd> 
      <mdss:geo param="geoId" concrete="false" value="${geoId}" political="false" spray="true" />
    </dd>
    <dt> <mdss:localize key="Insecticide"/> </dt>
    <dd>
      <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="brand.componentId" >
       <mjl:option selected="${brand != null && current.id == brand.id ? 'selected' : 'false'}">
          ${current.productName.termDisplayLabel.value}
       </mjl:option>
      </mjl:select>
    </dd>
    <dt> <mdss:localize key="Spray_Date"/> </dt>
    <dd> <mjl:input param="date" type="text" classes="DatePick NoFuture" id="sprayDate" value="${date}"/></dd>
    <dt> <mdss:localize key="Spray_Method"/> </dt>
    <dd>
      <mjl:group type="radio" var="current" varStatus="status" valueAttribute="enumName" items="${methods}" param="method">
        <mjl:groupOption checked="${current.enumName == method ? 'checked' : 'false'}">
            ${current.displayLabel}
        </mjl:groupOption>
      </mjl:group>
    </dd>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.ZoneSprayController.searchByParameters.mojo" name="search.button" value="${Localized_Search}" />
  </dl>
</mjl:form>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ZoneSprayExcelView" name="excelType"/>
</jsp:include>

<div id="cal1Container" class="yui-skin-sam"></div>
<%//out.flush();%>