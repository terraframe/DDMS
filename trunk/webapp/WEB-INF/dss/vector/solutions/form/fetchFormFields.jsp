<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<c:forEach items="${fields}" var="item">
  <li>${item.displayLabel}
    <mjl:input type="text" readonly="true" id="${item.id}" /> <!-- param="item.fieldName" /-->
    <a href="#" class="form-item-row-delete edit-mode-functionality" id="${item.id}">Delete</a>            
  </li>
</c:forEach>
