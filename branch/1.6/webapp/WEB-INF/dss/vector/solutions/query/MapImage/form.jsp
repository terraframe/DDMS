<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="dto" item="${item}">
  <mjl:dt attribute="imageFilePath">
    <mjl:input param="imageFilePath" type="text" />
  </mjl:dt>
  <mjl:dt attribute="imageName">
    <mjl:input param="imageName" type="text" />
  </mjl:dt>
  <mjl:dt attribute="imageXPosition">
    <mjl:input param="imageXPosition" type="text" />
  </mjl:dt>
  <mjl:dt attribute="imageYPosition">
    <mjl:input param="imageYPosition" type="text" />
  </mjl:dt>
</mjl:component>
