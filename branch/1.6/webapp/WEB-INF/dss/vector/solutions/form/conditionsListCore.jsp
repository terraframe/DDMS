<%@page import="com.runwaysdk.format.AbstractFormatFactory"%>
<%@page import="com.runwaysdk.system.metadata.DoubleConditionDTO"%>
<%@page import="com.runwaysdk.form.web.condition.DoubleCondition"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>


<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="com.runwaysdk.system.metadata.FieldConditionDTO"%>
<%@page import="dss.vector.solutions.util.ErrorUtility"%>


<%@page import="com.runwaysdk.dataaccess.ProgrammingErrorExceptionDTO"%>
<%@page import="com.runwaysdk.ClientException"%>

<%@page import="com.runwaysdk.system.metadata.CharacterConditionDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdFieldDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebSingleTermDTO"%>
<%@page import="com.runwaysdk.system.metadata.MdWebMultipleTermDTO"%>

<%@page import="com.runwaysdk.constants.MdWebSingleTermInfo"%>
<%@page import="com.runwaysdk.system.metadata.MdWebGeoDTO"%>
<c:forEach items="${conditions}" var="condition">
<c:set value="${condition}" scope="request" var="condition"></c:set>
<%
  try
  {
    FieldConditionDTO cond = (FieldConditionDTO) request.getAttribute("condition");
    MdFormAdminController.prepareConditionView(request, cond);
  }
  catch(Throwable t)
  {
    throw new ClientException(t);
  }
%>

<li id="${condition.id}">
<mjl:form name="${condition.id}_MdWebFormAdminController.editCondition.form" id="${condition.id}_MdWebFormAdminController.editCondition.form.id" method="POST">
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
    <c:choose>
      <c:when test="${termDisplayLabel != null}">
        <span id="${condition.id}_value">${termDisplayLabel}</span>
      </c:when>
      <c:when test="${geoDisplayLabel != null}">
        <span id="${condition.id}_value">${geoDisplayLabel}</span>
      </c:when>
      <c:when test="${isNumber}">
        <span id="${condition.id}_value">${conditionValue}</span>
      </c:when>
      <c:otherwise>
        <span id="${condition.id}_value">${condition.value}</span>
      </c:otherwise>
    </c:choose>
  </mjl:dt>
</dl>

  <div class="form-action-row" id="formActionRow">
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command id="${condition.id}_edit" value="${Localized_Edit}" action="dss.vector.solutions.form.MdFormAdminController.editCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.editCondition.button"/>
    <mdss:localize key="Delete" var="Localized_Delete" />
    <mjl:command id="${condition.id}_delete" value="${Localized_Delete}" action="dss.vector.solutions.form.MdFormAdminController.deleteCondition.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.deleteCondition.button" />
  </div>
  
</mjl:component>
</mjl:form>

<c:if test="${isDate}">
  <script type="text/javascript">
  (function(){
    var el = document.getElementById('${condition.id}_value');
    el.innerHTML = MDSS.Calendar.getLocalizedString(el.innerHTML);
  })();
  </script>
</c:if>

</li>
</c:forEach>
