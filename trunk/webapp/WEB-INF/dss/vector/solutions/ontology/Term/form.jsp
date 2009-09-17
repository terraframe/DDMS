<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="obsolete">
    <mjl:boolean param="obsolete" />
  </mjl:dt>
  <mjl:dt attribute="ontology">
    <mjl:select param="ontology" items="${ontology}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="termComment">
    <mjl:input param="termComment" type="text" />
  </mjl:dt>
  <mjl:dt attribute="termId">
    <mjl:input param="termId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="termName">
    <mjl:input param="termName" type="text" />
  </mjl:dt>
</mjl:component>
