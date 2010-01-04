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
    <mjl:select id="savedSearchList" var="current" valueAttribute="savedQueryId" items="${queryList}" param="savedSearch">
      <mjl:option>
        ${current.queryName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="geoHierarchy">
    <mjl:input type="hidden" param="mdAttribute" id="mdAttributeId" value="${mdAttributeId}" />
    <mjl:input type="hidden" param="geoHierarchy" id="geoHierarchyId" value="${geoHierarchyId}" />
    <select id="attrGeoSelect">
      <c:forEach items="${attrGeos}" var="attrGeo">
        <c:choose>
          <c:when test="${currentAttributeGeoHierarchy == attrGeo.concatId}">
            <option selected="selected" value="${attrGeo.mdAttributeId}:${attrGeo.geoHierarchyId}">
              ${attrGeo.geoHierarchyDisplayLabel} (${attrGeo.attributeDisplayLabel})
            </option>
          </c:when>
          <c:otherwise>
            <option value="${attrGeo.mdAttributeId}:${attrGeo.geoHierarchyId}">
              ${attrGeo.geoHierarchyDisplayLabel} (${attrGeo.attributeDisplayLabel})
            </option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt attribute="thematicUserAlias">
    <select name="layer.thematicUserAlias" id="thematicVariables">
      <option value=""></option>
      <c:forEach items="${thematicVars}" var="thematicVar">
        <c:choose>
          <c:when test="${thematicVar.userAlias == layer.thematicUserAlias}">
            <option value="${thematicVar.userAlias}" selected="selected">${thematicVar.displayLabel}</option>
          </c:when>
          <c:otherwise>
            <option value="${thematicVar.userAlias}">${thematicVar.displayLabel}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt attribute="renderAs">
    <mjl:radioGroup var="current" valueAttribute="enumName" items="${renderAsOptions}" param="renderAs">
      <mjl:radioOption
        value="${currentEnumName}"
        checked="${mjl:contains(layer.renderAsEnumNames, current.enumName) ? 'checked' : 'false'}">
        ${current.displayLabel} 
      </mjl:radioOption>
    </mjl:radioGroup>
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
  <ul id="categoryList">
  <mjl:command value="Exact_Category" action="dss.vector.solutions.query.NonRangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mjl:command value="Range_Category" action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
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
 <i><fmt:message key="Layer_Create_Required" /></i>
 <ul id="categoryList"></ul>
</c:otherwise>
</c:choose>
</div>