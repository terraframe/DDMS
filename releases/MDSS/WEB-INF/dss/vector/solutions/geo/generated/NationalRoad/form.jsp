<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="multiLineString">
    <mjl:input type="text" param="multiLineString" />
  </mjl:dt>
  <mjl:dt attribute="activated">
    <mjl:boolean param="activated" />
  </mjl:dt>
  <mjl:dt attribute="entityName">
    <mjl:input type="text" param="entityName" />
  </mjl:dt>
  <mjl:dt attribute="gazId">
    <mjl:input type="text" param="gazId" />
  </mjl:dt>
  <mjl:dt attribute="geoId">
    <mjl:input type="text" param="geoId" />
  </mjl:dt>
</mjl:component>
