<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>

<c:forEach items="${conditions}" var="condition">
<li id="${condition.id}">
<mjl:form name="MdWebFormAdminController.editCondition.form" id="MdWebFormAdminController.editCondition.form.id" method="POST">
<mjl:input type="hidden" param="conditionId" value="${condition.id}"></mjl:input>
<mjl:component item="${condition}" param="condition">
<dl>
  <mjl:dt attribute="definingMdField">
    ${definingMdFieldDisplay}
  </mjl:dt>
  <c:if test="${operations != null}">  
	  <mjl:dt attribute="operation">
	    ${condition.operationMd.enumItems[condition.operationEnumNames[0]]}
	  </mjl:dt>
  </c:if>
  
  <mjl:dt attribute="value">
    ${condition.value}
  </mjl:dt>
</dl>
</mjl:component>

  <div class="form-action-row" id="formActionRow">
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.form.MdFormAdminController.editCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.editCondition.button"/>
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.form.MdFormAdminController.deleteCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.deleteCondition.button" />
  </div>
  
</mjl:form>
</li>
</c:forEach>
