<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="estimated">
    <mjl:boolean param="estimated" />
  </mjl:dt>
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="growthRate">
    <mjl:input param="growthRate" type="text" />
  </mjl:dt>
  <mjl:dt attribute="population">
    <mjl:input param="population" type="text" />
  </mjl:dt>
  <mjl:dt attribute="yearOfData">
    <mjl:input param="yearOfData" type="text" />
  </mjl:dt>
</mjl:component>
