<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>


<div>
 <select name="mdFieldId" id="mdFieldId">
    <option value=""></option>
    <c:forEach items="${fields}" var="field">
      <option value="${field.id}">${field}</option>  
    </c:forEach>
  </select>
</div>

<div id="conditionDiv">
</div>
