<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="namespace">
    <mjl:input param="namespace" type="text" />
  </mjl:dt>
  <mjl:dt attribute="ontologyName">
    <mjl:input param="ontologyName" type="text" />
  </mjl:dt>
</mjl:component>
