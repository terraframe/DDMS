<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="geoEntityClass">
    <mjl:select param="geoEntityClass" items="${geoEntityClass}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="political">
    <mjl:boolean param="political" />
  </mjl:dt>
  <mjl:dt attribute="sprayTargetAllowed">
    <mjl:boolean param="sprayTargetAllowed" />
  </mjl:dt>
  <mjl:dt attribute="viewCreated">
    <mjl:boolean param="viewCreated" />
  </mjl:dt>
</mjl:component>
