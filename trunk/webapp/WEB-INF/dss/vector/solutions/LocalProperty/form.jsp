<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="editable">
    <mjl:boolean param="editable" />
  </mjl:dt>
  <mjl:dt attribute="propertyName">
    <mjl:input param="propertyName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyPackage">
    <mjl:input param="propertyPackage" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyType">
    <mjl:input param="propertyType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyValidator">
    <mjl:input param="propertyValidator" type="text" />
  </mjl:dt>
  <mjl:dt attribute="propertyValue">
    <mjl:input param="propertyValue" type="text" />
  </mjl:dt>
  <mjl:dt attribute="validValues">
    <mjl:input param="validValues" type="text" />
  </mjl:dt>
</mjl:component>
