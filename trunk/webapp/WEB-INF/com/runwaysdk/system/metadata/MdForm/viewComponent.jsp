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
<c:set scope="request" var="page_title" value="View_MdForm" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.MdForm.form.id" name="com.runwaysdk.system.metadata.MdForm.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="formMdClass">
        ${item.formMdClass.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="formName">
        ${item.formName}
      </mjl:dt>
      <mjl:dt attribute="displayLabel">
        ${item.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="exported">
        ${item.exported ? item.exportedMd.positiveDisplayLabel : item.exportedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="jsBase">
        ${item.jsBase}
      </mjl:dt>
      <mjl:dt attribute="jsStub">
        ${item.jsStub}
      </mjl:dt>
      <mjl:dt attribute="packageName">
        ${item.packageName}
      </mjl:dt>
      <mjl:dt attribute="typeName">
        ${item.typeName}
      </mjl:dt>
      <mjl:dt attribute="description">
        ${item.description}
      </mjl:dt>
      <mjl:dt attribute="remove">
        ${item.remove ? item.removeMd.positiveDisplayLabel : item.removeMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.MdForm.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.MdFormController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.MdForm.viewAll.link" action="com.runwaysdk.system.metadata.MdFormController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
