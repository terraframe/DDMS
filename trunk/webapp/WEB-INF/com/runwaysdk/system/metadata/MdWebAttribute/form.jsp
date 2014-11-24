<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
  <mjl:dt attribute="displayLabel">
    <mjl:input param="displayLabel" type="text" />
  </mjl:dt>
  <mjl:dt attribute="description">
    <mjl:input param="description" type="text" />
  </mjl:dt>
  <mjl:dt attribute="required">
    <mjl:boolean param="required" />
  </mjl:dt>
  <mjl:dt attribute="remove">
    <mjl:boolean param="remove" />
  </mjl:dt>
<c:if test="${isComposite}">
  <mjl:dt attribute="fieldOrder">
    <mjl:input param="fieldOrder" type="text" />
  </mjl:dt>
</c:if>
  <mjl:dt attribute="showOnViewAll">
    <mjl:boolean param="showOnViewAll" />
  </mjl:dt>
  