<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.InsecticideNozzle.form.name" id="dss.vector.solutions.irs.InsecticideNozzle.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.coverageMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="coverage" />
        <mjl:messages attribute="coverage">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enabledMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="enabled" />
        <mjl:messages attribute="enabled">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.InsecticideNozzleController.create.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.form.create.button" />
</mjl:form>
