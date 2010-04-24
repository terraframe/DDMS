<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="disease">
    <mjl:select param="disease" items="${_disease}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.diseaseEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.diseaseMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="term">
    <mdss:mo param="term" value="${term}" />
  </mjl:dt>
  <mjl:dt attribute="url">
    <mjl:select param="url" items="${url}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
