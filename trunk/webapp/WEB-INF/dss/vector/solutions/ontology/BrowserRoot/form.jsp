<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="term">
    <mdss:mo value="${term}" script="false" enabled="true" param="term" />
  </mjl:dt>
  <mjl:dt attribute="selectable">
    <mjl:boolean param="selectable" />
  </mjl:dt>
</mjl:component>
