<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.InsecticideBrand.form.name" id="dss.vector.solutions.irs.InsecticideBrand.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.activeIngredientMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.activeIngredient.keyName}" action="dss.vector.solutions.mo.ActiveIngredientController.view.mojo" name="dss.vector.solutions.mo.ActiveIngredient.form.view.link">
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
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
    <dt>
      <label>
        ${item.sachetsPerRefillMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sachetsPerRefill}
    </dd>
    <dt>
      <label>
        ${item.unitsMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.unitsEnumNames}">
          <li>
            ${item.unitsMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.weightMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.weight}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.InsecticideBrandController.edit.mojo" name="dss.vector.solutions.irs.InsecticideBrand.form.edit.button" />
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
        <mjl:commandLink display="" action="dss.vector.solutions.irs.InsecticideNozzleController.parentQuery.mojo" name="dss.vector.solutions.irs.InsecticideNozzle.parentQuery.link">
          <mjl:property value="${item.id}" name="parentId" />
        </mjl:commandLink>
      </li>
    </ul>
  </dd>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.InsecticideBrandController.viewAll.mojo" name="dss.vector.solutions.irs.InsecticideBrand.viewAll.link" />
