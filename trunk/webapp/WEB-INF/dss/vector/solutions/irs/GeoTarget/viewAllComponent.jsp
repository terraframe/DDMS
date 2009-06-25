<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Search_Geo_Targets"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<c:set var="now" value="<%=new java.util.Date()%>" scope="request"/>


<mjl:form name="dss.vector.solutions.irs.GeoTargetController.view.mojo" method="POST" id ="searchMosquitoCollections">
  <dl>
    <dt>
      <label><fmt:message key="Geo_Id"/></label>
    </dt>
    <dd>
      <mjl:input id="geoIdEl" param="geoId" type="text" maxlength="16" classes="geoInput"/>
      <mjl:input id="geoEntityId" param="geoEntity.componentId" type="hidden" />
    </dd>
    <dt>
      <label> <fmt:message key="Season"/></label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${seasons}" param="season.componentId" >
        <mjl:option>
          ${current.seasonName}, <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.startDate}" /> - <fmt:formatDate pattern="${dateFormatPattern}"  value="${current.endDate}" />
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.irs.GeoTargetController.view.mojo" name="search.button" value="Search"/>
  </dl>
</mjl:form>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>
<script type="text/javascript">
MDSS.AbstractSelectSearch.Political = false;
MDSS.AbstractSelectSearch.SprayTargetAllowed = true;
</script>