<jsp:include page="/WEB-INF/inlineError.jsp" />

<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.GeoHierarchy.form.name" id="dss.vector.solutions.geo.generated.GeoHierarchy.form.id" method="POST">
  <mjl:input type="hidden" param="geoHierarchyId" value="${geoHierarchyId}" />
  <mjl:component item="${view}" param="view">
    <mjl:input type="hidden" param="geoHierarchyId" />
    <mjl:input type="hidden" param="referenceId" />
    <dl>
      <dt>
        <label>
          ${view.displayLabelMd.displayLabel}
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
          ${view.descriptionMd.displayLabel}
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
          ${view.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
      </dd>
  </mjl:component>
    </dl>
  <mjl:command value="Update" action="dss.vector.solutions.geo.GeoEntityTypeController.updateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.updateDefinition.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.geo.GeoEntityTypeController.cancelUpdateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.cancelUpdateDefinition.button" />
</mjl:form>