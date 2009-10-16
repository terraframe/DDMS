<jsp:include page="/WEB-INF/inlineError.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.generated.GeoHierarchy.form.name" id="dss.vector.solutions.geo.generated.GeoHierarchy.form.id" method="POST">
  <mjl:input type="hidden" param="geoHierarchyId" value="${geoHierarchyId}" />
  <dl>
    <mjl:component item="${view}" param="view">
      <mjl:input type="hidden" param="geoHierarchyId" />
      <mjl:input type="hidden" param="referenceId" />
      <dt>
        <label>
          ${definition.typeNameMd.displayLabel}
        </label>
      </dt>
      <dd>
        ${definition.typeName}
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
          ${definition.parentTypeGeoHierarchyIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        ${parentLabel}
      </dd>
      <dt>
        <label>
        ${definition.termMd.displayLabel}
        </label>
      </dt>
      <dd>
        <span class="clickable" id="termBtn">
          <fmt:message key="Browser" />
        </span>
        <div class="ontologyDisplay" id="termDisplay">
          <c:choose>
            <c:when test="${term != null}">
              ${term.displayLabel}
            </c:when>
            <c:otherwise>
              <fmt:message key="no_value" />
            </c:otherwise>
          </c:choose>
        </div>
        <mjl:input value="${term != null ? term.id : ''}" type="hidden" param="term" id="term" />
      </dd>
      <dt>
        <label>
          ${definition.politicalMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="political" />
        <mjl:messages attribute="political">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.populationAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="populationAllowed" />
      </dd>
      <dt>
        <label>
          ${definition.sprayTargetAllowedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:boolean param="sprayTargetAllowed" />
        <mjl:messages attribute="sprayTargetAllowed">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${definition.spatialTypeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <ul>
          <c:forEach var="enumName" items="${definition.spatialTypeEnumNames}">
            <li>
              ${definition.spatialTypeMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>            
      </dd>      
    </mjl:component>
    <mjl:command value="Update" action="dss.vector.solutions.geo.GeoEntityTypeController.updateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.updateDefinition.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.geo.GeoEntityTypeController.cancelUpdateDefinition.mojo" name="dss.vector.solutions.geo.GeoEntityTypeController.form.cancelUpdateDefinition.button" />
  </dl>
</mjl:form>