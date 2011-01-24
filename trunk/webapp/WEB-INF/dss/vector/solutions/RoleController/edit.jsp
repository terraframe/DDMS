<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page_title" value="Roles_Edit" scope="request"/>

<mjl:form id="saveForm" name="dss.vector.solutions.RoleController.save.form" method="POST">
  <mjl:input type="hidden" param="id" value="${id}"/>
  
  <dl>
    <dt>
      <label>${view.usernameMd.displayLabel}</label>
    </dt>
    <dd>
      ${user.username}
    </dd>
    <dt>      
      <label>${view.rolesMd.displayLabel}</label>
    </dt>
    <c:forEach var="current" items="${roles}">
      <dd>
        <c:choose>
          <c:when test="${mjl:contains(assigned, current.id)}">
            <input type="checkbox" name="assigned" value="${current.id}" checked="checked"> ${current.displayLabel}
          </c:when>
          <c:otherwise>
          <input type="checkbox" name="assigned" value="${current.id}"> ${current.displayLabel}
          </c:otherwise>
        </c:choose>
      </dd>
    </c:forEach>        
    <dd>
      <mdss:localize key="Submit" var="Localized_Submit" />
      <mjl:command name="save" action="dss.vector.solutions.RoleController.save.mojo" value="${Localized_Submit}" />
    </dd>    
  </dl>
  
</mjl:form>

