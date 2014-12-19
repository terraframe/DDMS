<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="activated">
    <mjl:boolean param="activated" />
  </mjl:dt>
  <mjl:dt attribute="entityLabel">
    <mjl:input param="entityLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="geoData">
    <mjl:input param="geoData" type="text" />
  </mjl:dt>
  <mjl:dt attribute="geoId">
    <mjl:input param="geoId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="term">
    <mdss:mo param="term" value="${term}" script="false" />
  </mjl:dt>
</mjl:component>
