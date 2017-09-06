<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="geoEntity">
    <mdss:geo param="geoEntity" value="${item.geoEntity}" />
  </mjl:dt>
  <mjl:dt attribute="universal">
    <mjl:select valueAttribute="id" param="universal" var="current" items="${_universal}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="latitudeAttribute">
    <mjl:select valueAttribute="id" param="latitudeAttribute" var="current" items="${_latitudeAttribute}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="longitudeAttribute">
    <mjl:select valueAttribute="id" param="longitudeAttribute" var="current" items="${_longitudeAttribute}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="columnLabel">
    <mjl:input param="columnLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="target">
    <mjl:select valueAttribute="id" param="target" var="current" items="${_target}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="targetAttribute">
    <mjl:select valueAttribute="id" param="targetAttribute" var="current" items="${_targetAttribute}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
