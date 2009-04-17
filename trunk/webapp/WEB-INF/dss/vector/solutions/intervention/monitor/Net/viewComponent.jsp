<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
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
        ${item.netNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.netName}
    </dd>
    <dt>
      <label>
        ${item.parentNetMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parentNet.keyName}" action="dss.vector.solutions.intervention.monitor.NetController.view.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.view.link">
        <mjl:property value="${item.parentNet.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.NetController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.NetHeiarchyController.parentQuery.mojo" name="dss.vector.solutions.intervention.monitor.NetHeiarchy.parentQuery.link">
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
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.NetHeiarchyController.childQuery.mojo" name="dss.vector.solutions.intervention.monitor.NetHeiarchy.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.intervention.monitor.HouseholdNetController.childQuery.mojo" name="dss.vector.solutions.intervention.monitor.HouseholdNet.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.NetController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.Net.viewAll.link" />
