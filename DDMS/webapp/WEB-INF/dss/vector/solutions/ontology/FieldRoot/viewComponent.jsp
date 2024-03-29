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
<c:set var="page_title" value="View_FieldRoot" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.FieldRoot.form.name" id="dss.vector.solutions.ontology.FieldRoot.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <dt>
        <label>
          Ontology Field
        </label>
      </dt>
      <dd>
        <mjl:commandLink action="dss.vector.solutions.ontology.BrowserFieldController.view.mojo" name="dss.vector.solutions.ontology.BrowserField.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
          ${item.parent.keyName}          
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          Ontology Root
        </label>
      </dt>
      <dd>
        <mjl:commandLink action="dss.vector.solutions.ontology.BrowserRootController.view.mojo" name="dss.vector.solutions.ontology.BrowserRoot.form.view.link">
          <mjl:property value="${item.parentId}" name="id" />
          ${item.parent.keyName}          
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.ontology.FieldRootController.edit.mojo" name="dss.vector.solutions.ontology.FieldRoot.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.ontology.FieldRootController.viewAll.mojo" name="dss.vector.solutions.ontology.FieldRoot.viewAll.link">
  <mdss:localize key="View_All" />
</mjl:commandLink>
