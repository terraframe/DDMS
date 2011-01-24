<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set scope="request" var="page_title" value="Configure_universal_permissions" />


<mjl:form name="form.name" id="form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <mjl:input type="hidden" param="concreteId" value="${item.concreteId}" />        
    <mjl:input type="hidden" param="displayLabel" value="${item.displayLabel}" />        
  </mjl:component>

  <table class="displayTable">
    <tr>
      <th>${universalLabel}</th>
      <th>${permissionLabel}</th>
    </tr>
    <mjl:components items="${permissions}" param="permissions" var="view" varStatus="status">
      <mjl:input type="hidden" param="universalId" value="${view.universalId}"/>
      <mjl:input type="hidden" param="label" value="${view.label}"/>
      <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
        <td>
          ${view.label}
        </td>
        <td>
          <mdss:checkBoolean param="permission" showAttributeLabel="false" value="${view.permission}" />
        </td>
      </tr>
    </mjl:components>
  </table>

  <mdss:localize key="save" var="Localized_save" />

  <mjl:command value="${Localized_save}" action="dss.vector.solutions.permission.MDSSRoleController.setUniversalPermissions.mojo" name="save.button" />
</mjl:form>