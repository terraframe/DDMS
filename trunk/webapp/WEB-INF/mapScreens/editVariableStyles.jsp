<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.query.AbstractCategoryDTO"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>

<mjl:form name="dss.vector.solutions.query.ThematicLayer.form.name" id="dss.vector.solutions.query.ThematicLayer.form.id" method="POST">
<mjl:command value="Update" action="dss.vector.solutions.query.MappingController.updateThematicVariable.mojo" name="dss.vector.solutions.query.MappingController.form.updateThematicVariable.button" />
<mjl:command value="Cancel" action="dss.vector.solutions.query.MappingController.cancelLayer.mojo" name="dss.vector.solutions.query.MappingController.form.cancelLayer.button" />
<br />
<fmt:message key="Thematic_Variable" />:&nbsp;
<select name="variable">
  <option value=""></option>
  <c:forEach items="${variables}" var="variable">
    <c:choose>
      <c:when test="${thematicVariable != null && thematicVariable.entityAlias == variable.entityAlias && thematicVariable.attributeName == variable.attributeName}">
        <option value="${variable.entityAlias},${variable.attributeName},${variable.userAlias}" selected="selected">${variable.displayLabel}</option>
      </c:when>
      <c:otherwise>
        <option value="${variable.entityAlias},${variable.attributeName},${variable.userAlias}">${variable.displayLabel}</option>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</select>
</mjl:form>

<div id="categories">
<fmt:message key="Add_Category" />:
<mjl:commandLink display="Exact" classes="clickable" action="dss.vector.solutions.query.NonRangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<mjl:commandLink display="Range" classes="clickable" action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
<ul id="categoryList">
<c:forEach items="${categories}" var="category">
  <li>

    <%
      request.setAttribute("RangeCategoryClass", RangeCategoryDTO.CLASS);
    %>
    <c:choose>
      <c:when test="${category.type == RangeCategoryClass}">
        <dl id="${category.id}_dl">
          <dt>
            ${category.lowerBoundMd.displayLabel} - ${category.upperBoundMd.displayLabel}
          </dt>
          <dd>
            <div style='float: left'>
              <input type="hidden" name="type" value="${category.type}" />
              <input maxlength="7" type="hidden" name="dto.thematicColor" value="${category.thematicColor}" id="${category.id}_thematicColor" />
              <input class="bounds" type="text" value="${category.lowerBound}" />
              &nbsp;-&nbsp;
              <input class="bounds" type="text" value="${category.upperBound}" />
            </div>
            <div style='float: left'>
              <div class="colorPickerValue" id="${category.id}_opener" style="background-color: ${category.thematicColor}">&nbsp;</div>
            </div>
            <div style='float: left; margin-top: 3px; margin-left: 15px'>
              <img id="${category.id}_delBtn" src="imgs/icons/delete.png" class="clickable"/>
            </div>
          </dd>
        </dl>
      </c:when>
      <c:otherwise>
        <dl id="${category.id}_dl">
          <dt>
            ${category.exactValueMd.displayLabel}
          </dt>
          <dd>
            <div style='float: left'>
              <input type="hidden" name="type" value="${category.type}" />
              <input maxlength="7" type="hidden" name="dto.thematicColor" value="${category.thematicColor}" id="${category.id}_thematicColor" />
              <input class="bounds" type="text" value="${category.exactValue}" />
            </div>
            <div style='float: left'>
              <div class="colorPickerValue" id="${category.id}_opener" style="background-color: ${category.thematicColor}">&nbsp;</div>
            </div>
            <div style='float: left; margin-top: 3px; margin-left: 15px'>
              <img id="${category.id}_delBtn" src="imgs/icons/delete.png" class="clickable"/>
            </div>
          </dd>
        </dl>
      </c:otherwise>
    </c:choose>
<script type="text/javascript">
(function(){

  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${category.id}_opener', '${category.id}_thematicColor', '${category.thematicColor}');

  // deletes the category from the DOM
  YAHOO.util.Event.on("${category.id}_delBtn", 'click', function(e, dlId){
    var el = document.getElementById(dlId);
    el.parentNode.removeChild(el);
  }, "${category.id}_dl", this);

})();
</script>
  </li>
</c:forEach>
</ul>
</div>
