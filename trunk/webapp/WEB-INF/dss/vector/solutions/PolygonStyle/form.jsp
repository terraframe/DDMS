<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="fill">
    <mjl:input param="fill" type="text" />
  </mjl:dt>
  <mjl:dt attribute="stroke">
    <mjl:input param="stroke" type="text" />
  </mjl:dt>
  <mjl:dt attribute="strokeWidth">
    <mjl:input param="strokeWidth" type="text" />
  </mjl:dt>
</mjl:component>
