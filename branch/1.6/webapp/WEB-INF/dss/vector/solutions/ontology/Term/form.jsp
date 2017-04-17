<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mdss:localize key="Choice_Yes" var="TrueLabel" />
<mdss:localize key="Choice_No" var="FalseLabel" />

<mjl:component item="${item}" param="dto">
  <mjl:dt attribute="name">
    <mjl:input type="text" param="name" />
  </mjl:dt>
  <mjl:dt attribute="termDisplayLabel">
    <mjl:input type="text" param="termDisplayLabel" />
  </mjl:dt>
  <mjl:dt attribute="termId">
    <mjl:input type="text" param="termId" />
  </mjl:dt>
  <mjl:dt attribute="namespace">
    <mjl:input type="text" param="namespace" />
  </mjl:dt>
  <mjl:dt attribute="def">
    <mjl:input type="text" param="def" />
  </mjl:dt>
  <mjl:dt attribute="comment">
    <mjl:input type="text" param="comment" />
  </mjl:dt>
</mjl:component>
<c:choose>
  <c:when test="${isRoot}">
    <mjl:input param="inactive" value="${inactive}" type="hidden" />
  </c:when>
  <c:otherwise>
  <dt>
   <label for="inactive">${inactiveLabel}</label>
  </dt>
  <dd>
    <mjl:boolean param="inactive" falseLabel="${FalseLabel}" trueLabel="${TrueLabel}" value="${inactive}"  />
  </dd>
  </c:otherwise>
</c:choose>
