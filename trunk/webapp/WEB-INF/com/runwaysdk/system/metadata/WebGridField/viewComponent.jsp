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
<c:set scope="request" var="page_title" value="View_WebGridField" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="com.runwaysdk.system.metadata.WebGridField.form.id" name="com.runwaysdk.system.metadata.WebGridField.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebSingleTermGrid.form.view.link" action="com.runwaysdk.system.metadata.MdWebSingleTermGridController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
      <dt>
        <label>
          
        </label>
      </dt>
      <dd>
        <mjl:commandLink name="com.runwaysdk.system.metadata.MdWebPrimitive.form.view.link" action="com.runwaysdk.system.metadata.MdWebPrimitiveController.view.mojo">
          <mdss:localize key="${item.parent.keyName}" />
          <mjl:property name="id" value="${item.parentId}" />
        </mjl:commandLink>
      </dd>
    </mjl:component>
    <mjl:command name="com.runwaysdk.system.metadata.WebGridField.form.edit.button" value="Edit" action="com.runwaysdk.system.metadata.WebGridFieldController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="com.runwaysdk.system.metadata.WebGridField.viewAll.link" action="com.runwaysdk.system.metadata.WebGridFieldController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
