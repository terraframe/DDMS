<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:input param="propertyPackage" type="hidden" value="${item.propertyPackage}" />
  <mjl:input param="propertyName" type="hidden" value="${item.propertyName}" />
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>  
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyValue">
    <mjl:input param="propertyValue" type="text" />
  </mjl:dt>
</mjl:component>
