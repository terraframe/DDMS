<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="fill">
    <mjl:input param="fill" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <mjl:input param="fontFamily" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontSize">
    <mjl:input param="fontSize" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontStyle">
    <mjl:input param="fontStyle" type="text" />
  </mjl:dt>
</mjl:component>
