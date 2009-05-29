<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="page_title" value="Treatment_Grid_View" scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.TreatmentGrid.form.name" id="dss.vector.solutions.surveillance.TreatmentGrid.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.active}
    </dd>
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
        ${item.optionNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.optionName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.TreatmentGridController.edit.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.surveillance.CaseTreatmentController.childQuery.mojo" name="dss.vector.solutions.surveillance.CaseTreatment.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
      <li>
        <mjl:commandLink display="" action="dss.vector.solutions.surveillance.CaseTreatmentStockController.childQuery.mojo" name="dss.vector.solutions.surveillance.CaseTreatmentStock.childQuery.link">
          <mjl:property value="${item.id}" name="childId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.TreatmentGridController.viewAll.mojo" name="dss.vector.solutions.surveillance.TreatmentGrid.viewAll.link" />
