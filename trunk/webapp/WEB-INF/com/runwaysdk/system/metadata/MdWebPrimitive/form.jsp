<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>
  
  <c:if test="${fields != null}">  
    <mjl:dt attribute="isExpression">
      <mjl:boolean param="isExpression" id="expressionBool"/>
    </mjl:dt>
  
    <mjl:dt attribute="expression" id="expressionInputFieldLabel" style="display:none;">
      <mjl:input param="expression" type="textarea" id="expressionInputField" style="display:none;" />
    </mjl:dt>
  
    <dd>
      <div id="expressionCalcContainer" style="display:none;">
	      <div id="expressionAttributeButtonConainer" >
	        <c:forEach items="${fields}" var="field">
	    		<button class="expressionAttributeButton" value="${field.fieldName}">${field.displayLabel}</button>
	    	</c:forEach>
	      </div>
			  <div id="expressionOperatorButtonConainer">
				<button class="expressionOperatorButton"
					title='<mdss:localize key="additionTooltip"/>'>+</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="subtractionTooltip"/>'>-</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="multiplicationTooltip"/>'>*</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="divisionTooltip"/>'>/</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="remainderTooltip"/>'>%</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="equalityTooltip"/>'>==</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="notEqualTooltip"/>'>!=</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="lessThanTooltip"/>'><</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="lessThanEqualTooltip"/>'><=</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="greaterThanTooltip"/>'>></button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="greaterThanEqualTooltip"/>'>>=</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="orTooltip"/>'>or</button>
				<button class="expressionOperatorButton"
					title='<mdss:localize key="andTooltip"/>'>and</button>
			</div>
		</div>
    </dd>
  </c:if>
</mjl:component>