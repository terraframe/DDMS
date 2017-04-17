<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:component param="dto" item="${item}">
  <dl>
    <mjl:dt attribute="seasonLabel">
      <mjl:input param="seasonLabel" type="text" value="${item.seasonLabel.value}" />
    </mjl:dt>
    <mjl:dt attribute="startDate" type="text" classes="DatePick" />
    <mjl:dt attribute="endDate" type="text" classes="DatePick" />
  </dl>
</mjl:component>
