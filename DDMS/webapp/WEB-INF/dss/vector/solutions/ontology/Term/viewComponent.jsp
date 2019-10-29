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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="View_Term" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.Term.form.name" id="dss.vector.solutions.ontology.Term.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="obsolete">
        ${item.obsolete ? item.obsoleteMd.positiveDisplayLabel : item.obsoleteMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="ontology">
        ${item.ontology.id}
      </mjl:dt>
      <mjl:dt attribute="termComment">
        ${item.termComment}
      </mjl:dt>
      <mjl:dt attribute="termId">
        ${item.termId}
      </mjl:dt>
      <mjl:dt attribute="termName">
        ${item.termName}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.TermController.edit.mojo" name="dss.vector.solutions.ontology.Term.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.TermController.viewAll.mojo" name="dss.vector.solutions.ontology.Term.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
