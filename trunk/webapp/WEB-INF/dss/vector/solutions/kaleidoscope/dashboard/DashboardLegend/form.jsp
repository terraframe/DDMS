<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="groupedInLegend">
    <mjl:boolean param="groupedInLegend" />
  </mjl:dt>
  <mjl:dt attribute="legendXPosition">
    <mjl:input param="legendXPosition" type="text" />
  </mjl:dt>
  <mjl:dt attribute="legendYPosition">
    <mjl:input param="legendYPosition" type="text" />
  </mjl:dt>
</mjl:component>
