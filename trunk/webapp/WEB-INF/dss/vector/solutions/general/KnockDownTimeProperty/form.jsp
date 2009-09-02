<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="insecticide">
    <mjl:select param="insecticide" items="${insecticide}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lowerPercent">
    <mjl:input param="lowerPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lowerTime">
    <mjl:input param="lowerTime" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperPercent">
    <mjl:input param="upperPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperTime">
    <mjl:input param="upperTime" type="text" />
  </mjl:dt>
</mjl:component>
