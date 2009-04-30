<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.InsecticideBrand.form.name" id="dss.vector.solutions.irs.InsecticideBrand.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activeIngredientMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_InsecticideBrand_activeIngredient}" param="activeIngredient">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="activeIngredient">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.amountMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="amount" />
        <mjl:messages attribute="amount">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.enabledMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="enabled" />
        <mjl:messages attribute="enabled">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sachetsPerRefillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sachetsPerRefill" />
        <mjl:messages attribute="sachetsPerRefill">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.unitsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_irs_InsecticideBrand_units}" param="units">
          <mjl:option selected="${mjl:contains(item.unitsEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.unitsMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="units">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.weightMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="weight" />
        <mjl:messages attribute="weight">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.InsecticideBrandController.create.mojo" name="dss.vector.solutions.irs.InsecticideBrand.form.create.button" />
</mjl:form>
