<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.query.LayerController.form.name" id="dss.vector.solutions.query.LayerController.form.id" method="POST">
  <mjl:input type="hidden" param="layerId" value="${layerId}" />
  <mjl:component item="${geoStyle}" param="geometryStyle">
    <dl>
      <dt>
        <label>
          ${geoStyle.strokeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="stroke" />
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
    </dl>
  </mjl:component>
  <mjl:component item="${textStyle}" param="textStyle">
    <dl>
      <dt>
        <label>
          ${textStyle.fillMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="fill" />
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
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.query.LayerController.updateSummary.mojo" name="dss.vector.solutions.query.LayerController.updateSummary.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.query.LayerController.cancelSummary.mojo" name="dss.vector.solutions.query.LayerController.cancelSummary.button" />
</mjl:form>