<jsp:include page="/WEB-INF/inlineError.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.GeoEntity.form.name" id="dss.vector.solutions.geo.generated.GeoEntity.form.id" method="POST">
  <dl>
    <mjl:component item="${definition}" param="definition">
    <mjl:input type="hidden" param="parentGeoHierarchyId" />
      <dt>
        <label>
          ${definition.typeNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="typeName" />
        <mjl:messages attribute="typeName">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.displayLabelMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="displayLabel" />
        <mjl:messages attribute="displayLabel">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.descriptionMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="description" />
        <mjl:messages attribute="description">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
        ${definition.termMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mdss:mo param="term" value="${term}" script="false"/>
      </dd>
      <dt>
        <label>
          ${definition.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
      </dd>
      <dt>
        <label>
          ${definition.sprayTargetAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="sprayTargetAllowed" />
      </dd>
      <dt>
        <label>
          ${definition.populationAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="populationAllowed" />
      </dd>
    </mjl:component>
    <mjl:command value="Create" action="dss.vector.solutions.geo.GeoEntityTypeController.createDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.createDefinition.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.GeoEntityTypeController.cancelCreateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.cancelCreateDefinition.button" />
  </dl>
</mjl:form>