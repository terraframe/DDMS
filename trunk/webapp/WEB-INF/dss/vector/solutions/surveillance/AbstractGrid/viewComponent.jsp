<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.AbstractGrid.form.name" id="dss.vector.solutions.surveillance.AbstractGrid.form.id" method="POST">
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
        ${item.referralNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.referralName}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.surveillance.AbstractGridController.edit.mojo" name="dss.vector.solutions.surveillance.AbstractGrid.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.surveillance.AbstractGridController.viewAll.mojo" name="dss.vector.solutions.surveillance.AbstractGrid.viewAll.link" />
