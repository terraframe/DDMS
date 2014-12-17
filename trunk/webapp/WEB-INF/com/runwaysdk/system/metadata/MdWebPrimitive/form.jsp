<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebAttribute/form.jsp" %>

  <mjl:dt attribute="isExpression">
    <mjl:boolean param="isExpression" id="expressionBool"/>
  </mjl:dt>

  <!--   <dd> -->
<!--     <textarea id="expressionInputField" name="expression" wrap="hard" rows="4" cols="50" style="display:none;"> </textarea> -->
<!--   </dd> -->

<!-- <dd> -->
<!--     <input id="expressionInputField" style="" name="mdField.expression" type="textarea" wrap="hard" rows="4" cols="50" style="display:none;" > -->
<!-- </dd> -->
  
  <mjl:dt attribute="expression" id="expressionInputFieldLabel">
    <mjl:input param="expression" type="textarea" id="expressionInputField" style="display:none;" />
  </mjl:dt>
  
  <dd>
    <div id="expressionCalcContainer" style="display:none;">
	    <div id="expressionAttributeButtonConainer" >
	    	<c:forEach items="${fields}" var="field">
	    		<button class="expressionAttributeButton" value="${field.fieldName}">${field.displayLabel}</button>
	    	</c:forEach>
	    </div>
	    <div id="expressionOperatorButtonConainer" >
	      <button class="expressionOperatorButton">+</button>
	      <button class="expressionOperatorButton">-</button>
	      <button class="expressionOperatorButton">*</button>
	      <button class="expressionOperatorButton">/</button>
	      <button class="expressionOperatorButton">==</button>
	      <button class="expressionOperatorButton">!=</button>
	      <button class="expressionOperatorButton"><</button>
	      <button class="expressionOperatorButton"><=</button>
	      <button class="expressionOperatorButton">></button>
	      <button class="expressionOperatorButton">>=</button>
	      <button class="expressionOperatorButton">or</button>
	      <button class="expressionOperatorButton">and</button>
	      <button class="expressionOperatorButton">not</button>
	    </div>
	</div>
  </dd>

</mjl:component>