<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<mjl:component item="${category}" param="category">
  <mjl:dt attribute="lowerBoundStr">
    <mjl:input param="lowerBoundStr" type="text" />
  </mjl:dt>
  <mjl:dt attribute="upperBoundStr">
    <mjl:input param="upperBoundStr" type="text" />
  </mjl:dt>
</mjl:component>  
  
<jsp:include page="../Styles/form.jsp"></jsp:include>
