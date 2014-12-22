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
			<mjl:textarea param="expression" rows="3" cols="30" id="expressionInputField" style="display:none;" />
	    </mjl:dt>
	    
		<dd>
			<div id="expressionCalcContainer" style="display: none;">
				<div id="expressionAttributeButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionFieldAttributeLabel"/></label>
					<c:forEach items="${fields}" var="field">
						<button class="expressionButton" value="${field.fieldName}">${field.displayLabel}</button>
					</c:forEach>
				</div>

				<div id="expressionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionOperatorsLabel"/></label>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="additionTooltip"/>' value="+" >+</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="subtractionTooltip"/>' value="-" >-</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="multiplicationTooltip"/>' value="*" >*</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="divisionTooltip"/>' value="/" >/</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="remainderTooltip"/>' value="%" >%</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="equalityTooltip"/>' value="==" >==</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="notEqualTooltip"/>' value="!=" >!=</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="lessThanTooltip"/>' value="'<'" >&#60;</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="lessThanEqualTooltip"/>' value="'<='" >&#60;=</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="greaterThanTooltip"/>' value="'>'" >&#62;</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="greaterThanEqualTooltip"/>' value="'>='" >&#62;=</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="orTooltip"/>' value="or" >or</button>
					<button class="expressionOperatorButton"
						title='<mdss:localize key="andTooltip"/>' value="and" >and</button>
				</div>

				<div id="expressionFunctionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionStringMethodsLabel"/></label>
					<button class="expressionButton"
						title='<mdss:localize key="lowerCaseTooltip"/>' value=".toLowerCase()">toLowerCase</button>
					<button class="expressionButton"
						title='<mdss:localize key="upperCaseTooltip"/>' value=".toUpperCase()">toUpperCase</button>
					<button class="expressionButton"
						title='<mdss:localize key="lengthTooltip"/>' value=".length()">length</button>
					<button class="expressionButton"
						title='<mdss:localize key="replaceTooltip"/>' value=".replace(OLD_CHARACTER, NEW_CHARACTER)">replace</button>
					<button class="expressionButton"
						title='<mdss:localize key="replaceAllTooltip"/>' value=".replaceAll(REGX, NEW_CHARACTERS)">replaceAll</button>
					<button class="expressionButton"
						title='<mdss:localize key="substringTooltip"/>' value=".substring(BEGIN_INDEX, END_INDEX(OPTIONAL))">substring</button>
				</div>

				<div id="expressionConversionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionConversionMethodsLabel"/></label>
					<button class="expressionButton"
						title='<mdss:localize key="toStringTooltip"/>' value=".toString()">toString</button>
					<button class="expressionButton"
						title='<mdss:localize key="intValTooltip"/>' value=".intValue()">intValue</button>
					<button class="expressionButton"
						title='<mdss:localize key="doubleValTooltip"/>' value=".doubleValue()">doubleValue</button>
					<button class="expressionButton"
						title='<mdss:localize key="floatValTooltip"/>' value=".floatValue()">floatValue</button>
				</div>

				<div id="expressionMathButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionMathFunctionsLabel"/></label>
					<button class="expressionButton"
						title='<mdss:localize key="sqrtTooltip"/>' value="@@sqrt(VALUE)">sqrt</button>
					<button class="expressionButton"
						title='<mdss:localize key="sinTooltip"/>' value="@@sin(VALUE)">sin</button>
					<button class="expressionButton"
						title='<mdss:localize key="cosTooltip"/>' value="@@cos(VALUE)">cos</button>
					<button class="expressionButton"
						title='<mdss:localize key="tanTooltip"/>' value="@@tan(VALUE)">tan</button>
					<button class="expressionButton"
						title='<mdss:localize key="asinTooltip"/>' value="@@asin(VALUE)">asin</button>
					<button class="expressionButton"
						title='<mdss:localize key="acosTooltip"/>' value="@@acos(VALUE)">acos</button>
					<button class="expressionButton"
						title='<mdss:localize key="atan2Tooltip"/>' value="@@atan2(VALUE, VALUE)">atan2</button>
					<button class="expressionButton"
						title='<mdss:localize key="log10Tooltip"/>' value="@@log10(VALUE)">log1</button>
<!-- 					<button class="expressionButton" -->
<%-- 						title='<mdss:localize key="log101Tooltip"/>' value="@@log101(VALUE)">log101</button> --%>
					<button class="expressionButton"
						title='<mdss:localize key="logTooltip"/>' value="@@log(VALUE)">log1p</button>
				</div>
			</div>
		</dd>
	</c:if>
</mjl:component>