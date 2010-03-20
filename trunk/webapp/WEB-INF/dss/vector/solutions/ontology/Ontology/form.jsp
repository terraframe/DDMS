<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="defaultNamespace">
    <mjl:input type="text" param="defaultNamespace" />
  </mjl:dt>
  <mjl:dt attribute="keyName">
    <mjl:input type="text" param="keyName" />
  </mjl:dt>
  <mjl:dt attribute="title">
    <mjl:input type="text" param="title" />
  </mjl:dt>
</mjl:component>
