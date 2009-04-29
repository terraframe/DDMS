<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <mjl:dt attribute="displayLabel">
        <mjl:input type="text" param="displayLabel" />
        
</mjl:dt>
      <mjl:dt attribute="netName">
        <mjl:input type="text" param="netName" />
        
</mjl:dt>
      <mjl:dt attribute="parentNet">
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_intervention_monitor_Net_parentNet}" param="parentNet">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        
</mjl:dt>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.intervention.monitor.NetController.create.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.create.button" />
</mjl:form>
