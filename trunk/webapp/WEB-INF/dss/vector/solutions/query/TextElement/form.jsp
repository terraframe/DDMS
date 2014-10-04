<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="customTextElementId">
    <mjl:input param="customTextElementId" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontColor">
    <mjl:input param="fontColor" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <mjl:input param="fontFamily" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontSize">
    <mjl:input param="fontSize" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontStyle">
    <mjl:input param="fontStyle" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textValue">
    <mjl:input param="textValue" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textXPosition">
    <mjl:input param="textXPosition" type="text" />
  </mjl:dt>
  <mjl:dt attribute="textYPosition">
    <mjl:input param="textYPosition" type="text" />
  </mjl:dt>
</mjl:component>
