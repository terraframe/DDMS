<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<mjl:component item="${category}" param="category">
<dl>
  <mjl:dt attribute="exactValueStr">
    <mjl:input param="exactValueStr" type="text" />
  </mjl:dt>
</dl>
</mjl:component>  

<jsp:include page="../Styles/form.jsp"></jsp:include>
  
