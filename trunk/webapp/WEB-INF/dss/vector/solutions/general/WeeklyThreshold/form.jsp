<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="identification">
    <mjl:input type="text" param="identification" />
  </mjl:dt>
  <mjl:dt attribute="lastIdentification">
    <mjl:select var="current" valueAttribute="id" items="${lastIdentification}" param="lastIdentification">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="lastNotification">
    <mjl:select var="current" valueAttribute="id" items="${lastNotification}" param="lastNotification">
      <mjl:option>
        ${current.id}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="notification">
    <mjl:input type="text" param="notification" />
  </mjl:dt>
</mjl:component>
