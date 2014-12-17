<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
<%--   <%@include file="../MdWebAttribute/form.jsp" %> --%>
<%@include file="../MdWebPrimitive/form.jsp" %>
  <mjl:dt attribute="showOnSearch">
    <mjl:boolean param="showOnSearch" />
  </mjl:dt>    
  <mjl:dt attribute="displayLength">
    <mjl:input param="displayLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="maxLength">
    <mjl:input param="maxLength" type="text" />
  </mjl:dt>
  <mjl:dt attribute="unique">
    <mjl:boolean param="unique" />
  </mjl:dt>  
</mjl:component>
