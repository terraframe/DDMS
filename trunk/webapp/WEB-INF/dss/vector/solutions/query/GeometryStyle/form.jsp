<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="stroke">
    <mjl:input param="stroke" type="text" />
  </mjl:dt>
  <mjl:dt attribute="strokeWidth">
    <mjl:input param="strokeWidth" type="text" />
  </mjl:dt>
</mjl:component>
