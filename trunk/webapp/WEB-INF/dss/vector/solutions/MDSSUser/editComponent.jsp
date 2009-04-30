<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.MDSSUser.form.name" id="dss.vector.solutions.MDSSUser.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.inactiveMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="inactive" />
        <mjl:messages attribute="inactive">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.localeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${com_terraframe_mojo_system_Users_locale}" param="locale">
          <mjl:option selected="${mjl:contains(item.localeEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.localeMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="locale">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.passwordMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="password" />
        <mjl:messages attribute="password">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sessionLimitMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sessionLimit" />
        <mjl:messages attribute="sessionLimit">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.usernameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="username" />
        <mjl:messages attribute="username">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.MDSSUserController.update.mojo" name="dss.vector.solutions.MDSSUser.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.MDSSUserController.delete.mojo" name="dss.vector.solutions.MDSSUser.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.MDSSUserController.cancel.mojo" name="dss.vector.solutions.MDSSUser.form.cancel.button" />
</mjl:form>
