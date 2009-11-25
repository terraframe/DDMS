<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="fill">
    <mjl:input type="text" param="fill" />
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <mjl:input type="text" param="fontFamily" />
  </mjl:dt>
  <mjl:dt attribute="fontSize">
    <mjl:input type="text" param="fontSize" />
  </mjl:dt>
  <mjl:dt attribute="fontStyle">
    <mjl:input type="text" param="fontStyle" />
  </mjl:dt>
  <mjl:dt attribute="pointMarker">
    <mjl:select var="current" valueAttribute="enumName" items="${pointMarker}" param="pointMarker">
      <mjl:option selected="${mjl:contains(item.pointMarkerEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.pointMarkerMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="pointStroke">
    <mjl:input type="text" param="pointStroke" />
  </mjl:dt>
  <mjl:dt attribute="pointWidth">
    <mjl:input type="text" param="pointWidth" />
  </mjl:dt>
  <mjl:dt attribute="polygonFill">
    <mjl:input type="text" param="polygonFill" />
  </mjl:dt>
  <mjl:dt attribute="polygonStroke">
    <mjl:input type="text" param="polygonStroke" />
  </mjl:dt>
  <mjl:dt attribute="polygonWidth">
    <mjl:input type="text" param="polygonWidth" />
  </mjl:dt>
</mjl:component>
