<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="identification">
    <mjl:input param="identification" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lastIdentification">
    <mjl:select param="lastIdentification" items="${lastIdentification}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lastNotification">
    <mjl:select param="lastNotification" items="${lastNotification}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="notification">
    <mjl:input param="notification" type="text" />
  </mjl:dt>
</mjl:component>
