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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.ThematicVariable.form.name" id="dss.vector.solutions.query.ThematicVariable.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.attributeNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.attributeName}
    </dd>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.displayLabel}
    </dd>
    <dt>
      <label>
        ${item.entityAliasMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.entityAlias}
    </dd>
  </dl>
  <mdss:localize key="Edit" var="Localized_Edit" />
  <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.query.ThematicVariableController.edit.mojo" name="dss.vector.solutions.query.ThematicVariable.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink action="dss.vector.solutions.query.ThematicVariableController.viewAll.mojo" name="dss.vector.solutions.query.ThematicVariable.viewAll.link">
<mdss:localize key="View_All" />
</mjl:commandLink>
