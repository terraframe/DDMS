<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="displayLabel">
    <mjl:struct param="displayLabel">
      <mjl:dt attribute="defaultLocale">
        <mjl:input param="defaultLocale" type="text" />
      </mjl:dt>
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="enumName">
    <mjl:input param="enumName" type="text" />
  </mjl:dt>
</mjl:component>
