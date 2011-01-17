<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_Email" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.Email.form.id" name="dss.vector.solutions.general.Email.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="createDate">
        <span class="formatDate">${item.createDate}</span>
      </mjl:dt>
      <mjl:dt attribute="fromAddress">
        ${item.fromAddress}
      </mjl:dt>
      <mjl:dt attribute="toAddresses">
        ${item.toAddresses}
      </mjl:dt>
      <mjl:dt attribute="ccAddresses">
        ${item.ccAddresses}
      </mjl:dt>
      <mjl:dt attribute="bccAddresses">
        ${item.bccAddresses}
      </mjl:dt>
      <mjl:dt attribute="subject">
        ${item.subject}
      </mjl:dt>
      <mjl:dt attribute="body">
        ${item.body}
      </mjl:dt>
      <mjl:dt attribute="sentDate">
        <span class="formatDate">${item.sentDate}</span>
      </mjl:dt>
      <mjl:dt attribute="error">
        ${item.error}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.general.Email.form.delete.button" value="Delete" action="dss.vector.solutions.general.EmailController.delete.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.Email.viewAll.link" action="dss.vector.solutions.general.EmailController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
