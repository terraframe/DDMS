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
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.general.EmailConfiguration.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.general.EmailConfigurationController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.general.EmailConfiguration.viewAll.link" action="dss.vector.solutions.general.EmailConfigurationController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
