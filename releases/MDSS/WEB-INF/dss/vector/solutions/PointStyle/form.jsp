<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="wellKnownName">
    <mjl:select param="wellKnownName" items="${wellKnownName}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.wellKnownNameEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.wellKnownNameMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="stroke">
    <mjl:input param="stroke" type="text" />
  </mjl:dt>
  <mjl:dt attribute="strokeWidth">
    <mjl:input param="strokeWidth" type="text" />
  </mjl:dt>
</mjl:component>
