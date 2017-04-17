<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="displayLabel">
    <mjl:struct param="displayLabel">
      <mjl:dt attribute="defaultLocale">
        <mjl:input type="text" param="defaultLocale" />
      </mjl:dt>
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="enumName">
    <mjl:input type="text" param="enumName" />
  </mjl:dt>
</mjl:component>
