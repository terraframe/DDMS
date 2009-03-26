<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.name" id="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.assayMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.assay.keyName}" action="dss.vector.solutions.entomology.assay.LarvaeAssayController.view.mojo" name="dss.vector.solutions.entomology.assay.LarvaeAssay.form.view.link">
        <mjl:property value="${item.assay.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.periodMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.period}
    </dd>
    <dt>
      <label>
        ${item.quantityDeadMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.quantityDead}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.edit.mojo" name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.entomology.assay.LarvaeTestIntervalController.viewAll.mojo" name="dss.vector.solutions.entomology.assay.LarvaeTestInterval.viewAll.link" />
