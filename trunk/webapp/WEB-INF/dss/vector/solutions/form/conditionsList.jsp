<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>


<div class="viewConditions">
  <div class="addConditionSection">
	 <select name="mdFieldId" id="definingMdFieldSelect">
	    <c:forEach items="${fields}" var="field">
	      <option value="${field.id}">${field}</option>  
	    </c:forEach>
	  </select>
	  <a href="#" class="condition-button-add" id="createNewCondition">&nbsp;</a>
	</div>
  <div id="newConditionForm">
  </div>
  <div>
    <ul id="conditionsList">
      <%@include file="conditionsListCore.jsp" %>
    </ul>
  </div>
</div>
