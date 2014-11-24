<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
  <mjl:dt attribute="showOnSearch">
    <mjl:boolean param="showOnSearch" />
  </mjl:dt>    
  <mjl:dt attribute="height">
    <mjl:input param="height" type="text" />
  </mjl:dt>
  <mjl:dt attribute="width">
    <mjl:input param="width" type="text" />
  </mjl:dt>
</mjl:component>
