<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:input param="propertyPackage" type="hidden" value="${item.propertyPackage}" />
  <mjl:input param="propertyType" type="hidden" value="${item.propertyType}" />
  <mjl:input param="propertyValidator" type="hidden" value="${item.propertyValidator}" />
  <mjl:input param="validValues" type="hidden" value="${item.validValues}" />
  <mjl:input param="propertyName" type="hidden" value="${item.propertyName}" />
  <mjl:input param="editable" type="hidden" value="${item.editable}" />
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
