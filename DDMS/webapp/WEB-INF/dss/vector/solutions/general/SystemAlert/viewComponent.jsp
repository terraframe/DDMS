<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_SystemAlert" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.general.SystemAlert.form.id" name="dss.vector.solutions.general.SystemAlert.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="isOnscreenActive">
        ${item.isOnscreenActive ? item.isOnscreenActiveMd.positiveDisplayLabel : item.isOnscreenActiveMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="isEmailActive">
        ${item.isEmailActive ? item.isEmailActiveMd.positiveDisplayLabel : item.isEmailActiveMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="emailFromAddress">
        ${item.emailFromAddress}
      </mjl:dt>
      <mjl:dt attribute="emailToAddresses">
        ${item.emailToAddresses}
      </mjl:dt>
      <mjl:dt attribute="emailCcAddresses">
        ${item.emailCcAddresses}
      </mjl:dt>
      <mjl:dt attribute="emailBccAddresses">
        ${item.emailBccAddresses}
      </mjl:dt>
      <mjl:dt attribute="emailBody">
        ${item.emailBody}
      </mjl:dt>
      <mjl:dt attribute="emailSubject">
        ${item.emailSubject}
      </mjl:dt>
      <mjl:dt attribute="emailTemplateVariables">
        <mjl:struct param="emailTemplateVariables">
            ${item.emailTemplateVariables}
        </mjl:struct>
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.general.SystemAlert.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.general.SystemAlertController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.SystemAlert.viewAll.link" action="dss.vector.solutions.general.SystemAlertController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
