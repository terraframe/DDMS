<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
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
          ${item.enabledMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="enabled" />
        <mjl:messages attribute="enabled">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.isAbstractMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="isAbstract" />
        <mjl:messages attribute="isAbstract">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.netNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="netName" />
        <mjl:messages attribute="netName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.parentNetMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_intervention_monitor_Net_parentNet}" param="parentNet">
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
  <mjl:command value="Update" action="dss.vector.solutions.intervention.monitor.NetController.update.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.intervention.monitor.NetController.delete.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.intervention.monitor.NetController.cancel.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.cancel.button" />
</mjl:form>
