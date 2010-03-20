<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="namespace">
    <mjl:input type="text" param="namespace" />
  </mjl:dt>
  <mjl:dt attribute="ontologyName">
    <mjl:input type="text" param="ontologyName" />
  </mjl:dt>
</mjl:component>
