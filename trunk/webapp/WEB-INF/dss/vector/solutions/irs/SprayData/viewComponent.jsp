<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.SprayData.form.name" id="dss.vector.solutions.irs.SprayData.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.brandMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.brand.keyName}" action="dss.vector.solutions.irs.InsecticideBrandController.view.mojo" name="dss.vector.solutions.irs.InsecticideBrand.form.view.link">
        <mjl:property value="${item.brand.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.geoEntityMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.geoEntity.keyName}" action="dss.vector.solutions.geo.generated.GeoEntityController.view.mojo" name="dss.vector.solutions.geo.generated.GeoEntity.form.view.link">
        <mjl:property value="${item.geoEntity.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.sprayDateMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sprayDate}
    </dd>
    <dt>
      <label>
        ${item.sprayMethodMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.sprayMethodEnumNames}">
          <li>
            ${item.sprayMethodMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
    <dt>
      <label>
        ${item.surfaceTypeMd.displayLabel}
      </label>
    </dt>
    <dd>
      <ul>
        <c:forEach var="enumName" items="${item.surfaceTypeEnumNames}">
          <li>
            ${item.surfaceTypeMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.SprayDataController.edit.mojo" name="dss.vector.solutions.irs.SprayData.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.irs.SprayDataController.viewAll.mojo" name="dss.vector.solutions.irs.SprayData.viewAll.link" />
