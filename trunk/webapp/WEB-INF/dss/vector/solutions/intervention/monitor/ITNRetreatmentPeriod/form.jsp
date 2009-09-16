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
  <mjl:dt attribute="enabled">
    <mjl:boolean param="enabled" />
  </mjl:dt>
  <mjl:dt attribute="periodName">
    <mjl:input param="periodName" type="text" />
  </mjl:dt>
</mjl:component>
