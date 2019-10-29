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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set scope="request" var="page_title" value="Configure_url_permissions" />


<mjl:form name="form.name" id="form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />        
    <mjl:input type="hidden" param="displayLabel" value="${item.displayLabel}" />        
  </mjl:component>

  <table class="displayTable">
    <tr>
      <th>${urlLabel}</th>
      <th>${permissionLabel}</th>
    </tr>  
    <mjl:components items="${permissions}" param="permissions" var="view" varStatus="status">
      <mjl:input type="hidden" param="urlId" value="${view.urlId}"/>
      <mjl:input type="hidden" param="label" value="${view.label}"/>
            
      <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
        <td>
          ${view.label}
        </td>
        <td>
          <mjl:group param="permission" var="current" items="${options}" type="radio" valueAttribute="enumName">
            <mjl:groupOption checked="${mjl:contains(view.permissionEnumNames, current.enumName) ? 'checked' : 'false'}">
              ${current.displayLabel}
            </mjl:groupOption>
          </mjl:group>
        </td>
      </tr>
    </mjl:components>
  </table>

  <mdss:localize key="save" var="Localized_save" />

  <mjl:command value="${Localized_save}" action="dss.vector.solutions.permission.MDSSRoleController.setPermissions.mojo" name="save.button" />
</mjl:form>