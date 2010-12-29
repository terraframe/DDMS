<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="displayName">
    ${item.displayName}
  </mjl:dt>
  <mjl:dt attribute="isOnscreenActive">
    <mjl:boolean param="isOnscreenActive" />
  </mjl:dt>
  <mjl:dt attribute="isEmailActive">
    <mjl:boolean param="isEmailActive" />
  </mjl:dt>
  <mjl:dt attribute="emailFromAddress">
    <mjl:input param="emailFromAddress" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailToAddresses">
    <mjl:input param="emailToAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailCcAddresses">
    <mjl:input param="emailCcAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailBccAddresses">
    <mjl:input param="emailBccAddresses" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailSubject">
    <mjl:input param="emailSubject" type="text" />
  </mjl:dt>
  <mjl:dt attribute="emailBody">
    <mjl:textarea param="emailBody" rows="5" />
    <i><b>${templateLabel}:</b> ${templateVariables}</i>
  </mjl:dt>

</mjl:component>