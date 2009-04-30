<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.ZoneSpray.form.name" id="dss.vector.solutions.irs.ZoneSpray.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
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
  <mjl:command value="Update" action="dss.vector.solutions.irs.ZoneSprayController.update.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.ZoneSprayController.delete.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.ZoneSprayController.cancel.mojo" name="dss.vector.solutions.irs.ZoneSpray.form.cancel.button" />
</mjl:form>
