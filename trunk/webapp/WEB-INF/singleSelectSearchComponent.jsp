<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dss.vector.solutions.MDSSInfo"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="f" %>

<div style="width: 725px; margin-top: 15px;" >
<div id="selectSearchComponent">
  <dl>
  <c:forEach items="${views}" var="view" varStatus="status">
  <dt id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_dt">
    <c:if test="${!status.first}">
      <input type="checkbox" checked="checked" class="searchToggle" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_toggle" />&nbsp;
    </c:if>
    ${view.displayLabel}
  </dt>
  <dd id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_dd">
    <div class="typeContainer" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_container">
      <c:if test="${!status.first}">
      <input type="text" class="ajaxSearch" value="" style="width: 225px" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_search" /><br />
      <div id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_results" class="ajaxResults"></div>
    </c:if>
    <select disabled="disabled" style="width: 250px" name="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}">
    <c:if test="${!status.first}">
      <option value="DEFAULT"><f:message key="Select_One"/></option>
    </c:if>
    </select>
      <c:choose>
        <c:when test="${status.first}">
          <a href="#" id="treeOpener" style="margin-left: 20px"><img src="./imgs/icons/world.png" style="margin-right: 5px;"/>Tree</a><br />
            <div class="yui-skin-sam" id="treeViewContainer" style="background-color:white">
              <div id="treeView"></div>
            </div>
        </c:when>
        <c:otherwise>
          <input type="text" class="manualEntry" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_manualEntry" size="6" /><input type="button" class="manualSearch" id="${view.typeName}_manualSearch" value="Search" />
        </c:otherwise>
      </c:choose>
    </div>
  </dd>
  </c:forEach>
  </dl>
</div>
<div id="searchSelection">
    <h3><f:message key="Current_Selection" /></h3>
    <hr />
    <span id="currentSelection"></span>
</div>
</div>