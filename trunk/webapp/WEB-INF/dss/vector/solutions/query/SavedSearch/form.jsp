<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="config">
    <mjl:input param="config" type="text" />
  </mjl:dt>
  <mjl:dt attribute="csvFile">
    <mjl:input param="csvFile" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryName">
    <mjl:input param="queryName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryType">
    <mjl:input param="queryType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryXml">
    <mjl:input param="queryXml" type="text" />
  </mjl:dt>
  <mjl:dt attribute="templateFile">
    <mjl:input param="templateFile" type="text" />
  </mjl:dt>
  <mjl:dt attribute="thematicLayer">
    <mjl:select param="thematicLayer" items="${thematicLayer}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
