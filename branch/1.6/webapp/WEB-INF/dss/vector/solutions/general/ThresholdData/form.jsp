<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="season">
    <mjl:select param="season" items="${season}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
