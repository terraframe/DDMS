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
      <c:when test="${thematicVariable == variable}">
        <option value="${variable}" selected="selected">${labels[variable]}</option>
      </c:when>
      <c:otherwise>
        <option value="${variable}">${labels[variable]}</option>
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
      AbstractCategoryDTO category = (AbstractCategoryDTO) request.getAttribute("category");
      if(category instanceof RangeCategoryDTO)
      {
        %>

        <%
      }
      else
      {
    	%>
        <%
      }
    %>
  </li>
</c:forEach>
</ul>
</div>
