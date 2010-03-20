<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <mjl:component item="${item}" param="dto">
      <mjl:input param="insecticide" type="hidden" value="${item.insecticide.id}"/>
      <mjl:dt attribute="insecticide">
          ${item.insecticide.displayLabel}      
      </mjl:dt>
      <mjl:dt attribute="lowerPercent" type="text"/>
      <mjl:dt attribute="lowerTime" type="text"/>
      <mjl:dt attribute="upperPercent" type="text"/>
      <mjl:dt attribute="upperTime" type="text"/>
    </mjl:component>
