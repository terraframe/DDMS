<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>


<ul>
<c:forEach items="${results}" var="item">
  <li>
    <span>${item.id} - ${item.displayLabel} - ${item.description}</span>
  </li>
</c:forEach>
</ul>