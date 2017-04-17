<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="ontologyRelationships">
    <mjl:select var="current" valueAttribute="enumName" items="${ontologyRelationships}" param="ontologyRelationships">
      <mjl:option selected="${mjl:contains(item.ontologyRelationshipsEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.ontologyRelationshipsMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="mdAttribute">
    <mjl:select var="current" valueAttribute="id" items="${mdAttribute}" param="mdAttribute">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
