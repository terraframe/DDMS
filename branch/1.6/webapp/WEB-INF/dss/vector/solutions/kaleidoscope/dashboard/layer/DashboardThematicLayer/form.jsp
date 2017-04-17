<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="aggregationStrategy">
    <mjl:select valueAttribute="id" param="aggregationStrategy" var="current" items="${_aggregationStrategy}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="aggregationType">
    <mjl:select valueAttribute="enumName" param="aggregationType" var="current" items="${_aggregationType}">
      <mjl:option selected="${mjl:contains(item.aggregationTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.aggregationTypeMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="geoNode">
    <mjl:select valueAttribute="id" param="geoNode" var="current" items="${_geoNode}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="mdAttribute">
    <mjl:select valueAttribute="id" param="mdAttribute" var="current" items="${_mdAttribute}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="BBoxIncluded">
    <mjl:boolean param="BBoxIncluded" />
  </mjl:dt>
  <mjl:dt attribute="activeByDefault">
    <mjl:boolean param="activeByDefault" />
  </mjl:dt>
  <mjl:dt attribute="dashboardLegend">
    <mjl:struct param="dashboardLegend">
      <mjl:dt attribute="groupedInLegend">
        <mjl:boolean param="groupedInLegend" />
      </mjl:dt>
      <mjl:dt attribute="legendXPosition">
        <mjl:input param="legendXPosition" type="text" />
      </mjl:dt>
      <mjl:dt attribute="legendYPosition">
        <mjl:input param="legendYPosition" type="text" />
      </mjl:dt>
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="dashboardMap">
    <mjl:select valueAttribute="id" param="dashboardMap" var="current" items="${_dashboardMap}">
      <mjl:option>
        ${current.keyName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="displayInLegend">
    <mjl:boolean param="displayInLegend" />
  </mjl:dt>
  <mjl:dt classes="DatePick" attribute="lastPublishDate" type="text" />
  <mjl:dt attribute="layerEnabled">
    <mjl:boolean param="layerEnabled" />
  </mjl:dt>
  <mjl:dt attribute="layerType">
    <mjl:select valueAttribute="enumName" param="layerType" var="current" items="${_layerType}">
      <mjl:option selected="${mjl:contains(item.layerTypeEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.layerTypeMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="nameLabel">
    <mjl:input param="nameLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="viewName">
    <mjl:input param="viewName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="virtual">
    <mjl:boolean param="virtual" />
  </mjl:dt>
</mjl:component>
