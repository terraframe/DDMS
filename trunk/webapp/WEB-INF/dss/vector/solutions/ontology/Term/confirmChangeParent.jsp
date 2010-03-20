<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<mjl:form name="confirmChangeParentForm" id="confirmChangeParentForm" method="POST">
<input type="hidden" id="childId" name="childId" value="${childId}" />
<input type="hidden" id="parentId" name="parentId" value="${parentId}" />
<div>
  <div class="modalAlertBox">
    <span>${message}</span>
  </div>
  <div class="modalAlertBox">
    <mjl:command value="Choice_Yes" action="dss.vector.solutions.ontology.TermController.doNotClone.mojo" name="dss.vector.solutions.ontology.TermController.doClone" />
    <mjl:command value="Choice_No" action="dss.vector.solutions.ontology.TermController.doClone.mojo" name="dss.vector.solutions.ontology.TermController.doNotClone" />
  </div>
</div>
</mjl:form>