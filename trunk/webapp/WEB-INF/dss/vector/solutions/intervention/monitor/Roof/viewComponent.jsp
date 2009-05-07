<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
      ${item.displayLabel}
    </dd>
    <dt>
      <label>
        ${item.roofNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.roofName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.RoofController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Roof.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.RoofHeiarchyController.parentQuery.mojo" name="dss.vector.solutions.intervention.monitor.RoofHeiarchy.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.RoofHeiarchyController.childQuery.mojo" name="dss.vector.solutions.intervention.monitor.RoofHeiarchy.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.RoofController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Roof.viewAll.link" />
