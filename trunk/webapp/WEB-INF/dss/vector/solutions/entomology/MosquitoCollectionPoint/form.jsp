<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="collectionId">
    <mjl:input param="collectionId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="dateCollected">
    <mjl:input param="dateCollected" type="text" />
  </mjl:dt>
  <mjl:dt attribute="geoEntity">
    <mjl:select param="geoEntity" items="${geoEntity}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
</mjl:component>
