<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.UniversalLayer.form.name" id="dss.vector.solutions.query.UniversalLayer.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.geoHierarchyMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_query_Layer_geoHierarchy}" param="geoHierarchy">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="geoHierarchy">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.geometryStyleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_query_Layer_geometryStyle}" param="geometryStyle">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="geometryStyle">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sldFileMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sldFile" />
        <mjl:messages attribute="sldFile">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.textStyleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_query_Layer_textStyle}" param="textStyle">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="textStyle">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.query.UniversalLayerController.create.mojo" name="dss.vector.solutions.query.UniversalLayer.form.create.button" />
</mjl:form>
