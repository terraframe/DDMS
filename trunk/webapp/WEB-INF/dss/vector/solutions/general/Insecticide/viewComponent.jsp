<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_Insecticide"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.Insecticide.form.name" id="dss.vector.solutions.general.Insecticide.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activeIngredientMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.activeIngredient.displayLabel}" action="dss.vector.solutions.mo.ActiveIngredientController.view.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.view.link">
        <mjl:property value="${item.activeIngredient.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.amountMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.amount}
    </dd>
    <dt>
      <label>
        ${item.unitsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.units.displayLabel}
    </dd>
  </dl>
<%--
  <mjl:command value="Edit" action="dss.vector.solutions.general.InsecticideController.edit.mojo" name="dss.vector.solutions.general.Insecticide.form.edit.button" />
  --%><br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.general.InsecticideController.viewAll.mojo" name="dss.vector.solutions.general.Insecticide.viewAll.link">
  <fmt:message key="View_All" />
</mjl:commandLink>
