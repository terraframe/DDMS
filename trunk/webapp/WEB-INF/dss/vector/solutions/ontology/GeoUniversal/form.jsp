<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="description">
    <mjl:input type="text" param="description" />
  </mjl:dt>
  <mjl:dt attribute="obsolete">
    <mjl:boolean param="obsolete" />
  </mjl:dt>
  <mjl:dt attribute="ontology">
    <mjl:select var="current" valueAttribute="id" items="${ontology}" param="ontology">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="termComment">
    <mjl:input type="text" param="termComment" />
  </mjl:dt>
  <mjl:dt attribute="termId">
    <mjl:input type="text" param="termId" />
  </mjl:dt>
  <mjl:dt attribute="termName">
    <mjl:input type="text" param="termName" />
  </mjl:dt>
</mjl:component>
