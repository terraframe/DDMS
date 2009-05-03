<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.AbstractSpray.form.name" id="dss.vector.solutions.irs.AbstractSpray.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.sprayDataMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.sprayData.keyName}" action="dss.vector.solutions.irs.SprayDataController.view.mojo" name="dss.vector.solutions.irs.SprayData.form.view.link">
        <mjl:property value="${item.sprayData.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.AbstractSprayController.edit.mojo" name="dss.vector.solutions.irs.AbstractSpray.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.irs.AbstractSprayController.viewAll.mojo" name="dss.vector.solutions.irs.AbstractSpray.viewAll.link" />
