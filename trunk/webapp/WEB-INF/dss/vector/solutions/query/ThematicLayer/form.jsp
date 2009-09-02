<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="thematicVariable">
    <mjl:select param="thematicVariable" items="${thematicVariable}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="viewCreated">
    <mjl:boolean param="viewCreated" />
  </mjl:dt>
  <mjl:dt attribute="geoHierarchy">
    <mjl:select param="geoHierarchy" items="${geoHierarchy}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="geometryStyle">
    <mjl:select param="geometryStyle" items="${geometryStyle}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="sldFile">
    <mjl:input param="sldFile" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textStyle">
    <mjl:select param="textStyle" items="${textStyle}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="viewName">
    <mjl:input param="viewName" type="text" />
  </mjl:dt>
</mjl:component>
