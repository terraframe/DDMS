<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="dss.vector.solutions.query.GeometryStyle"%>
<%@page import="dss.vector.solutions.query.GeometryStyleDTO"%>
<%@page import="dss.vector.solutions.PolygonStyle"%>
<%@page import="dss.vector.solutions.PolygonStyleDTO"%><mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.MappingController.form.name" id="dss.vector.solutions.query.MappingController.form.id" method="POST">
  <mjl:input type="hidden" param="layerId" value="${layerId}" />
  <mjl:component item="${geoStyle}" param="geometryStyle">
    ${geoStyle.md.displayLabel}
    <dl>
      <dt>
        <label>
          ${geoStyle.strokeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="hidden" param="stroke" value="${geoStyle.stroke}" id="${geoStyle.id}_stroke" />
        <div class="colorPickerValue" id="${geoStyle.id}_stroke_opener" style="background-color: ${geoStyle.stroke}">&nbsp;</div>
        <mjl:messages attribute="stroke">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${geoStyle.strokeWidthMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="strokeWidth" />
        <mjl:messages attribute="strokeWidth">
          <mjl:message />
        </mjl:messages>
      </dd>
      <c:if test="${hasFill}">
      <dt>
        <label>
          ${geoStyle.fillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="hidden" param="fill" value="${geoStyle.fill}" id="${geoStyle.id}_fill" />
        <div class="colorPickerValue" id="${geoStyle.id}_fill_opener" style="background-color: ${geoStyle.fill}">&nbsp;</div>
        <mjl:messages attribute="fill">
          <mjl:message />
        </mjl:messages>
      </dd>        
      </c:if>
    </dl>
  </mjl:component>
  <mjl:component item="${textStyle}" param="textStyle">
    ${textStyle.md.displayLabel}
    <dl>
      <dt>
        <label>
          ${textStyle.fillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="hidden" param="fill" value="${textStyle.fill}" id="${textStyle.id}_fill" />
        <div class="colorPickerValue" id="${textStyle.id}_fill_opener" style="background-color: ${textStyle.fill}">&nbsp;</div>
        <mjl:messages attribute="fill">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${textStyle.fontFamilyMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontFamily" />
        <mjl:messages attribute="fontFamily">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${textStyle.fontSizeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontSize" />
        <mjl:messages attribute="fontSize">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${textStyle.fontStyleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fontStyle" />
        <mjl:messages attribute="fontStyle">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
<script type="text/javascript">
(function(){
	
  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${geoStyle.id}_stroke_opener', '${geoStyle.id}_stroke', '${geoStyle.stroke}');

  <c:if test="${hasFill}">
    picker.attach('${geoStyle.id}_fill_opener', '${geoStyle.id}_fill', '${geoStyle.fill}');
  </c:if>
  picker.attach('${textStyle.id}_fill_opener', '${textStyle.id}_fill', '${textStyle.fill}');

})();
</script>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.MappingController.updateLayer.mojo" name="dss.vector.solutions.query.MappingController.updateLayer.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.MappingController.cancelLayer.mojo" name="dss.vector.solutions.query.MappingController.cancelLayer.button" />
</mjl:form>
