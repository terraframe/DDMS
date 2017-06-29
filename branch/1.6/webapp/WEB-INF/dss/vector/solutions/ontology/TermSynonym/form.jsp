<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>

<jsp:include page="/WEB-INF/selectSearchTerm.jsp" />

<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="entityName">
    <mjl:input param="entityName" />
    <mjl:string param="entityName" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="collectionDate" type="text" />
  <mjl:dt attribute="collectionId">
    <mjl:input param="collectionId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="collectionMethod">
    <mdss:mo param="collectionMethod" value="${collectionMethod}" />
  </mjl:dt>
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="lifeStage">
    <mjl:select param="lifeStage" items="${lifeStage}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.lifeStageEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.lifeStageMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lifeStageName">
    <mjl:input param="lifeStageName" type="text" />
  </mjl:dt>
</mjl:component>
