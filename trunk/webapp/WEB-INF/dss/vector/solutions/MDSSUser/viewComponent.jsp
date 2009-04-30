<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.MDSSUser.form.name" id="dss.vector.solutions.MDSSUser.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.inactiveMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.inactive}
    </dd>
    <dt>
      <label>
        ${item.localeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.localeEnumNames}">
          <li>
            ${item.localeMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.passwordMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.password}
    </dd>
    <dt>
      <label>
        ${item.sessionLimitMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sessionLimit}
    </dd>
    <dt>
      <label>
        ${item.usernameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.username}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.MDSSUserController.edit.mojo" name="dss.vector.solutions.MDSSUser.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Parent Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="Roles" action="com.terraframe.mojo.system.AssignmentsController.parentQuery.mojo" name="com.terraframe.mojo.system.Assignments.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
      <li>
        <mjl:commandLink display="MdType" action="com.terraframe.mojo.system.TypePermissionController.parentQuery.mojo" name="com.terraframe.mojo.system.TypePermission.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.query.PersistsSearchController.parentQuery.mojo" name="dss.vector.solutions.query.PersistsSearch.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.MDSSUserController.viewAll.mojo" name="dss.vector.solutions.MDSSUser.viewAll.link" />
