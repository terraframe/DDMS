<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="attributeName">
    <mjl:input param="attributeName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="entityAlias">
    <mjl:input param="entityAlias" type="text" />
  </mjl:dt>
  <mjl:dt attribute="userAlias">
    <mjl:input param="userAlias" type="text" />
  </mjl:dt>
</mjl:component>
