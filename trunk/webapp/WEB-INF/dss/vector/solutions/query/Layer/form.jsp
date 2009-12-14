<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>

<%
  request.setAttribute("RangeClass", RangeCategoryDTO.CLASS);
%>



<mjl:component item="${layer}" param="layer">
  <input type="hidden" id="layerId" value="${layer.id}" />
  <mjl:dt attribute="layerName">
    <mjl:input type="text" param="layerName" />
  </mjl:dt>
  <mjl:dt attribute="savedSearch">
    <mjl:select var="current" valueAttribute="savedQueryId" items="${queryList}" param="savedSearch">
      <mjl:option>
        ${current.queryName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="opacity">
    <mjl:input type="text" param="opacity" />
  </mjl:dt>
</mjl:component>
<jsp:include page="../Styles/form.jsp"></jsp:include>

<div id="categories">
<fmt:message key="Add_Category" />: 
<c:choose>
<c:when test="${!isNewInstance}">
  <mjl:command value="Exact_Category" action="dss.vector.solutions.query.NonRangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mjl:command value="Range_Category" action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <ul id="categoryList">
    <c:forEach items="${categories}" var="category">
      <li>
      <c:set var="category" value="${category}" scope="request"></c:set>
      <c:choose>
        <c:when test="${category.type == RangeClass}">
          <jsp:include page="../RangeCategory/summaryView.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
          <jsp:include page="../NonRangeCategory/summaryView.jsp"></jsp:include>
        </c:otherwise>
      </c:choose>
    </li>
    </c:forEach>
  </ul>
</c:when>
<c:otherwise>
 <i>*Layer Must Be Created First</i>
</c:otherwise>
</c:choose>
</div>