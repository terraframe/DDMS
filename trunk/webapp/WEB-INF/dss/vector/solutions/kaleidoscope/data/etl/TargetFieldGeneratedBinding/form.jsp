<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
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
