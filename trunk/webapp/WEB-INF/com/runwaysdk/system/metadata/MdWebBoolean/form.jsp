<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<mdss:localize key="BOOLEAN_NONE" var="none"/>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
  <mjl:dt attribute="showOnSearch">
    <mjl:boolean param="showOnSearch" />
  </mjl:dt>    
  <mjl:dt attribute="defaultValue">
    <mdss:boolean param="defaultValue" noneLabel="${none}" />
  </mjl:dt>  
</mjl:component>
