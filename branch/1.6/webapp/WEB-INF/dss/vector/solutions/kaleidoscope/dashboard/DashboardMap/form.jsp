<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="activeBaseMap">
    <mjl:input param="activeBaseMap" type="text" />
  </mjl:dt>
  <mjl:dt attribute="dashboard">
    <mjl:select valueAttribute="id" param="dashboard" var="current" items="${dashboard}">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="name">
    <mjl:input param="name" type="text" />
  </mjl:dt>
</mjl:component>
