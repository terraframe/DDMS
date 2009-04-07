<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.surveillance.CaseTreatmentStock.form.name" id="dss.vector.solutions.surveillance.CaseTreatmentStock.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.outOfStockMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="outOfStock" />
        <mjl:messages attribute="outOfStock">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.surveillance.CaseTreatmentStockController.create.mojo" name="dss.vector.solutions.surveillance.CaseTreatmentStock.form.create.button" />
</mjl:form>
