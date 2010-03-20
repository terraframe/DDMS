<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:input param="insecticide" type="hidden" value="${item.insecticide.id}"/>
  <mjl:dt attribute="insecticide">
    ${item.insecticide.displayLabel}      
  </mjl:dt>
  <mjl:dt attribute="lowerPercent">
    <mjl:input param="lowerPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lowerTime">
    <mjl:input param="lowerTime" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperPercent">
    <mjl:input param="upperPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperTime">
    <mjl:input param="upperTime" type="text" />
  </mjl:dt>
</mjl:component>
