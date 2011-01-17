<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_EmailConfiguration" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.EmailConfiguration.form.id" name="dss.vector.solutions.general.EmailConfiguration.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="emailServer">
        ${item.emailServer}
      </mjl:dt>
      <mjl:dt attribute="protocol">
        <ul>
          <c:forEach items="${item.protocolEnumNames}" var="enumName">
            <li>
              ${item.protocolMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="emailUserid">
        ${item.emailUserid}
      </mjl:dt>
      <mjl:dt attribute="emailPassword">
        ${item.emailPassword}
      </mjl:dt>
      <mjl:dt attribute="retry">
        ${item.retry}
      </mjl:dt>
      <mjl:dt attribute="timeout">
        ${item.timeout}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.general.EmailConfiguration.form.edit.button" value="Edit" action="dss.vector.solutions.general.EmailConfigurationController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.EmailConfiguration.viewAll.link" action="dss.vector.solutions.general.EmailConfigurationController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
