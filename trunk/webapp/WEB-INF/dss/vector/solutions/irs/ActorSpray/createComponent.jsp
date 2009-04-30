<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ActorSpray.form.name" id="dss.vector.solutions.irs.ActorSpray.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.targetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target" />
        <mjl:messages attribute="target">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.teamSprayWeekMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="teamSprayWeek" />
        <mjl:messages attribute="teamSprayWeek">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayDataMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_AbstractSpray_sprayData}" param="sprayData">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sprayData">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.ActorSprayController.create.mojo" name="dss.vector.solutions.irs.ActorSpray.form.create.button" />
</mjl:form>
