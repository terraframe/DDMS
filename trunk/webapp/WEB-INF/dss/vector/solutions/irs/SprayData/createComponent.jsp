<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayData.form.name" id="dss.vector.solutions.irs.SprayData.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.brandMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_SprayData_brand}" param="brand">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="brand">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_SprayData_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="geoEntity">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayDateMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sprayDate" />
        <mjl:messages attribute="sprayDate">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayMethodMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_irs_SprayData_sprayMethod}" param="sprayMethod">
          <mjl:option selected="${mjl:contains(item.sprayMethodEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.sprayMethodMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="sprayMethod">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.surfaceTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_irs_SprayData_surfaceType}" param="surfaceType">
          <mjl:option selected="${mjl:contains(item.surfaceTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
            ${item.surfaceTypeMd.enumItems[current.enumName]}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="surfaceType">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.irs.SprayDataController.create.mojo" name="dss.vector.solutions.irs.SprayData.form.create.button" />
</mjl:form>
