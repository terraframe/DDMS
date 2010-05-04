<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input type="hidden" param="displayLabel" value="${item.displayLabel}"/>
  <mjl:dt attribute="displayLabel">
    ${item.displayLabel}
  </mjl:dt>
  <mjl:dt attribute="thresholdValue">
    <mjl:input param="thresholdValue" type="text" />
  </mjl:dt>
</mjl:component>
