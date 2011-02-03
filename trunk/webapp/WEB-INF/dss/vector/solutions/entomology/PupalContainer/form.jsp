<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="containerId">
    <mjl:input param="containerId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="containerLength">
    <mjl:input param="containerLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="containerType">
    <mdss:mo param="containerType" value="${containerType}" />
  </mjl:dt>
  <mjl:dt attribute="diameter">
    <mjl:input param="diameter" type="text" />
  </mjl:dt>
  <mjl:dt attribute="drawdownFrequency">
    <mdss:mo param="drawdownFrequency" value="${drawdownFrequency}" />
  </mjl:dt>
  <mjl:dt attribute="drawdownPercent">
    <mjl:input param="drawdownPercent" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fillFrequency">
    <mdss:mo param="fillFrequency" value="${fillFrequency}" />
  </mjl:dt>
  <mjl:dt attribute="fillMethod">
    <mdss:mo param="fillMethod" value="${fillMethod}" />
  </mjl:dt>
  <mjl:dt attribute="height">
    <mjl:input param="height" type="text" />
  </mjl:dt>
  <mjl:dt attribute="lid">
    <mdss:mo param="lid" value="${lid}" />
  </mjl:dt>
  <mjl:dt attribute="openingDiameter">
    <mjl:input param="openingDiameter" type="text" />
  </mjl:dt>
  <mjl:dt attribute="openingLength">
    <mjl:input param="openingLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="openingWidth">
    <mjl:input param="openingWidth" type="text" />
  </mjl:dt>
  <mjl:dt attribute="premise">
    <mjl:select param="premise" items="${_premise}" var="current" valueAttribute="id">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="roof">
    <mdss:mo param="roof" value="${roof}" />
  </mjl:dt>
  <mjl:dt attribute="shading">
    <mdss:mo param="shading" value="${shading}" />
  </mjl:dt>
  <mjl:dt attribute="shape">
    <mjl:select param="shape" items="${_shape}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.shapeEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.shapeMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="width">
    <mjl:input param="width" type="text" />
  </mjl:dt>
</mjl:component>
