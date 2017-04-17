<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="displayLabel">
    ${item.displayLabel}
    <mjl:input type="hidden" param="displayLabel" value="${item.displayLabel}" />
  </mjl:dt>
  <mjl:dt attribute="description">
    ${item.description}
    <mjl:input type="hidden" param="description" value="${item.description}" />
  </mjl:dt>
  <mjl:dt attribute="propertyValue">
    <mjl:input param="propertyValue" type="text" />
  </mjl:dt>
</mjl:component>
