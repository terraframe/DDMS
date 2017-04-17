<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="columnHeader">
    <mjl:input param="columnHeader" type="text" />
  </mjl:dt>
  <mjl:dt attribute="columnLabel">
    <mjl:input param="columnLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="columnType">
    <mjl:input param="columnType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="mdAttribute">
    <mjl:select valueAttribute="id" param="mdAttribute" var="current" items="${_mdAttribute}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sourceDefinition">
    <mjl:select valueAttribute="id" param="sourceDefinition" var="current" items="${_sourceDefinition}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
