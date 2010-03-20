<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="endDate">
    <mjl:input param="endDate" type="text" />
  </mjl:dt>
  <mjl:dt attribute="seasonName">
    <mjl:input param="seasonName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="startDate">
    <mjl:input param="startDate" type="text" />
  </mjl:dt>
</mjl:component>
