<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.InsecticideNozzle.form.name" id="dss.vector.solutions.irs.InsecticideNozzle.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        InsecticideBrand
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.parent.keyName}" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo" name="dss.vector.solutions.irs.InsecticideBrand.form.view.link">
        <mjl:property value="${item.parentId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        Nozzle
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.child.keyName}" action="dss.vector.solutions.irs.NozzleController.view.mojo" name="dss.vector.solutions.irs.Nozzle.form.view.link">
        <mjl:property value="${item.childId}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.coverageMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.coverage}
    </dd>
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.InsecticideNozzleController.edit.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.InsecticideNozzleController.viewAll.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.viewAll.link" />
