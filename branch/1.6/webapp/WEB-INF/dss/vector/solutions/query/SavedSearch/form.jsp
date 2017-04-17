<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="config">
    <mjl:input param="config" type="text" />
  </mjl:dt>
  <mjl:dt attribute="csvFile">
    <mjl:input param="csvFile" type="text" />
  </mjl:dt>
  <mjl:dt attribute="disease">
    <mjl:select param="disease" items="${_disease}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="mappable">
    <mjl:boolean param="mappable" />
  </mjl:dt>
  <mjl:dt attribute="queryName">
    <mjl:input param="queryName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryType">
    <mjl:input param="queryType" type="text" />
  </mjl:dt>
  <mjl:dt attribute="queryXml">
    <mjl:input param="queryXml" type="text" />
  </mjl:dt>
  <mjl:dt attribute="templateFile">
    <mjl:input param="templateFile" type="text" />
  </mjl:dt>
</mjl:component>
