<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Roof.form.name" id="dss.vector.solutions.intervention.monitor.Roof.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <dl>
          <mjl:struct param="displayLabel">
            <dt>
              <label>
                ${item.displayLabel.defaultLocaleMd.displayLabel}
              </label>
            </dt>
            <dd>
              <mjl:input type="text" param="defaultLocale" />
              <mjl:messages attribute="defaultLocale">
                <mjl:message />
              </mjl:messages>
            </dd>
          </mjl:struct>
        </dl>
      </dd>
      <dt>
        <label>
          ${item.roofNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="roofName" />
        <mjl:messages attribute="roofName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.parentNetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${parentRoofs}" param="parentRoof">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="parentNet">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.RoofController.create.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.create.button" />
</mjl:form>
