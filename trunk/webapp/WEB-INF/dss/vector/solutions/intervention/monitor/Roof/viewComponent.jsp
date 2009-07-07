<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Roof"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Roof.form.name" id="dss.vector.solutions.intervention.monitor.Roof.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.roofNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.roofName}
    </dd>
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>    
    <dt>
      <label>
        ${item.parentRoofMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.parentRoof.displayLabel}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.RoofController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.RoofController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Roof.viewAll.link" />
