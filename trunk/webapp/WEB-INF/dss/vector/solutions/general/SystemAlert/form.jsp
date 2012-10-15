<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:forEach items="${item.alertTypeEnumNames}" var="enumName">
  <c:set scope="request" var="page_title_suffix" value=" - ${item.alertTypeMd.enumItems[enumName]}" />
</c:forEach>

<c:set scope="request" var="page_title" value="Edit_SystemAlert" />

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
  <mjl:dt attribute="emailSubjectText">
    <mjl:input param="emailSubjectText" type="text" value="${item.emailSubjectText}"/>
  </mjl:dt>
  <mjl:dt attribute="emailBodyText">
    <mjl:textarea param="emailBodyText"  value="${item.emailBodyText}" rows="5" />
    <i><b>${templateLabel}:</b> ${templateVariables}</i>
  </mjl:dt>

</mjl:component>