<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.Nozzle.form.name" id="dss.vector.solutions.irs.Nozzle.form.id" method="POST">
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
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
    <dt>
      <label>
        ${item.ratioMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.ratio}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.NozzleController.edit.mojo" name="dss.vector.solutions.irs.Nozzle.form.edit.button" />
  <br />
</mjl:form>
<dl>
  <dt>
    <label>
      Child Relationships
    </label>
  </dt>
  <dd>
    <ul>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.irs.InsecticideNozzleController.childQuery.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.NozzleController.viewAll.mojo" name="dss.vector.solutions.irs.Nozzle.viewAll.link" />
