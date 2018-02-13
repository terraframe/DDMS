<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
						<c:choose>
							<c:when test="${field.type=='com.runwaysdk.system.metadata.MdWebSingleTerm'}">
								<button class="expressionButton" value="${fn:replace(field.displayLabel.value,' ', '')}.toString()">${field.displayLabel}</button>
							</c:when>
							<c:when test="${field.type=='com.runwaysdk.system.metadata.MdWebGeo'}">
								<button class="expressionButton" value="${fn:replace(field.displayLabel.value,' ', '')}.toString()">${field.displayLabel}</button>
							</c:when>
							<c:otherwise>
								<button class="expressionButton" value="${fn:replace(field.fieldName,' ', '')}">${field.displayLabel}</button>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

				<div id="expressionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionOperatorsLabel"/></label>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="additionTooltip"/>" value="+" ><mdss:localize key="exprBuilderFuncPlus"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="subtractionTooltip"/>" value="-" ><mdss:localize key="exprBuilderFuncMinus"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="multiplicationTooltip"/>" value="*" ><mdss:localize key="exprBuilderFuncMultiply"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="divisionTooltip"/>" value="/" ><mdss:localize key="exprBuilderFuncDivide"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="remainderTooltip"/>" value="%" ><mdss:localize key="exprBuilderFuncModulus"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="equalityTooltip"/>" value="==" ><mdss:localize key="exprBuilderFuncEquals"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="notEqualTooltip"/>" value="!=" ><mdss:localize key="exprBuilderFuncNotEquals"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="lessThanTooltip"/>" value="'<'" ><mdss:localize key="exprBuilderFuncLessThan"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="lessThanEqualTooltip"/>" value="'<='" ><mdss:localize key="exprBuilderFuncLessThanEquals"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="greaterThanTooltip"/>" value="'>'" ><mdss:localize key="exprBuilderFuncGreaterThan"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="greaterThanEqualTooltip"/>" value="'>='" ><mdss:localize key="exprBuilderFuncGreaterThanEquals"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="orTooltip"/>" value="or" ><mdss:localize key="exprBuilderFuncOr"/></button>
					<button class="expressionOperatorButton"
						title="<mdss:localize key="andTooltip"/>" value="and" ><mdss:localize key="exprBuilderFuncAnd"/></button>
				</div>

				<div id="expressionFunctionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionStringMethodsLabel"/></label>
					<button class="expressionButton"
						title="<mdss:localize key="lowerCaseTooltip"/>" value=".toLowerCase()"><mdss:localize key="exprBuilderFuncToLower"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="upperCaseTooltip"/>" value=".toUpperCase()"><mdss:localize key="exprBuilderFuncToUpper"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="lengthTooltip"/>" value=".length()"><mdss:localize key="exprBuilderFuncLength"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="replaceTooltip"/>" value=".replace(OLD_CHARACTER, NEW_CHARACTER)"><mdss:localize key="exprBuilderFuncReplace"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="replaceAllTooltip"/>" value=".replaceAll(REGX, NEW_CHARACTERS)"><mdss:localize key="exprBuilderFuncReplaceAll"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="substringTooltip"/>" value=".substring(BEGIN_INDEX, END_INDEX(OPTIONAL))"><mdss:localize key="exprBuilderFuncSubstring"/></button>
				</div>

				<div id="expressionConversionButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionConversionMethodsLabel"/></label>
					<button class="expressionButton"
						title="<mdss:localize key="toStringTooltip"/>" value=".toString()"><mdss:localize key="exprBuilderFuncToString"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="intValTooltip"/>" value=".intValue()"><mdss:localize key="exprBuilderFuncIntValue"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="doubleValTooltip"/>" value=".doubleValue()"><mdss:localize key="exprBuilderFuncDoubleValue"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="floatValTooltip"/>" value=".floatValue()"><mdss:localize key="exprBuilderFuncFloatValue"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="parseBoolTooltip"/>" value="@Boolean@parseBoolean(STRING_VALUE)"><mdss:localize key="exprBuilderFuncParseBoolean"/></button>	
					<button class="expressionButton"
						title="<mdss:localize key="parseIntTooltip"/>" value="@Integer@parseInt(STRING_VALUE)"><mdss:localize key="exprBuilderFuncParseInteger"/></button>	
					<button class="expressionButton"
						title="<mdss:localize key="parseLongTooltip"/>" value="@Long@parseLong(STRING_VALUE)"><mdss:localize key="exprBuilderFuncParseLong"/></button>	
					<button class="expressionButton"
						title="<mdss:localize key="parseFloatTooltip"/>" value="@Float@parseFloat(STRING_VALUE)"><mdss:localize key="exprBuilderFuncParseFloat"/></button>	
				</div>

				<div id="expressionMathButtonConainer" class="expressionButtonContainers">
					<label class="expressionContainerLabel" title=""><mdss:localize key="expressionMathFunctionsLabel"/></label>
					<button class="expressionButton"
						title="<mdss:localize key="sqrtTooltip"/>" value="@@sqrt(VALUE)"><mdss:localize key="exprBuilderFuncSquareRoot"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="sinTooltip"/>" value="@@sin(VALUE)"><mdss:localize key="exprBuilderFuncSin"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="cosTooltip"/>" value="@@cos(VALUE)"><mdss:localize key="exprBuilderFuncCos"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="tanTooltip"/>" value="@@tan(VALUE)"><mdss:localize key="exprBuilderFuncTan"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="asinTooltip"/>" value="@@asin(VALUE)"><mdss:localize key="exprBuilderFuncAsin"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="acosTooltip"/>" value="@@acos(VALUE)"><mdss:localize key="exprBuilderFuncAcos"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="atan2Tooltip"/>" value="@@atan2(VALUE, VALUE)"><mdss:localize key="exprBuilderFuncAtan2"/></button>
					<button class="expressionButton"
						title="<mdss:localize key="log10Tooltip"/>" value="@@log10(VALUE)"><mdss:localize key="exprBuilderFuncLog10"/></button>
<!-- 					<button class="expressionButton" -->
<%-- 						title="<mdss:localize key="log101Tooltip"/>" value="@@log101(VALUE)">log101</button> --%>
					<button class="expressionButton"
						title="<mdss:localize key="logTooltip"/>" value="@@log(VALUE)"><mdss:localize key="exprBuilderFuncLn"/></button>
				</div>
			</div>
		</dd>
	</c:if>
</mjl:component>