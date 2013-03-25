<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
  <mjl:dt attribute="startRange">
    <mjl:input param="startRange" type="text" value="${startRangeFormatted}" />
  </mjl:dt>
  <mjl:dt attribute="endRange">
    <mjl:input param="endRange" type="text" value="${endRangeFormatted}" />
  </mjl:dt>
</mjl:component>
