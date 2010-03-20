<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="definition">
    <mjl:input param="definition" type="text" />
  </mjl:dt>
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
  <mjl:dt attribute="inheritsTerm">
    <mjl:input param="inheritsTerm" type="text" />
  </mjl:dt>
  <mjl:dt attribute="inheritsTermName">
    <mjl:input param="inheritsTermName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="oboNamespace">
    <mjl:input param="oboNamespace" type="text" />
  </mjl:dt>
  <mjl:dt attribute="termId">
    <mjl:input param="termId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="termName">
    <mjl:input param="termName" type="text" />
  </mjl:dt>
</mjl:component>
