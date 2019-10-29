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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="View_SavedSearch" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.SavedSearch.form.id" name="dss.vector.solutions.query.SavedSearch.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="config">
        ${item.config}
      </mjl:dt>
      <mjl:dt attribute="csvFile">
        ${item.csvFile}
      </mjl:dt>
      <mjl:dt attribute="disease">
        ${item.disease.keyName}
      </mjl:dt>
      <mjl:dt attribute="mappable">
        ${item.mappable ? item.mappableMd.positiveDisplayLabel : item.mappableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="queryName">
        ${item.queryName}
      </mjl:dt>
      <mjl:dt attribute="queryType">
        ${item.queryType}
      </mjl:dt>
      <mjl:dt attribute="queryXml">
        ${item.queryXml}
      </mjl:dt>
      <mjl:dt attribute="templateFile">
        ${item.templateFile}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.query.SavedSearch.form.edit.button" value="Edit" action="dss.vector.solutions.query.SavedSearchController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.query.SavedSearch.viewAll.link" action="dss.vector.solutions.query.SavedSearchController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
