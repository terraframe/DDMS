<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="aggregatedPremiseUniversal">
    <mjl:select param="aggregatedPremiseUniversal" items="${_aggregatedPremiseUniversal}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="comments">
    <mjl:input param="comments" type="text" />
  </mjl:dt>
  <mjl:dt attribute="disease">
    <mjl:select param="disease" items="${_disease}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="endDate" type="text" />
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="individulPremiseUniversal">
    <mjl:select param="individulPremiseUniversal" items="${_individulPremiseUniversal}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="startDate" type="text" />
</mjl:component>
