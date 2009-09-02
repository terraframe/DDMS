<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="lowerBound">
    <mjl:input param="lowerBound" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperBound">
    <mjl:input param="upperBound" type="text" />
  </mjl:dt>
  <mjl:dt attribute="thematicColor">
    <mjl:input param="thematicColor" type="text" />
  </mjl:dt>
</mjl:component>
