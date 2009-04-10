<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dss.vector.solutions.MDSSInfo"%>

<div id="selectSearchComponent">
  <dl>
  <c:forEach items="${views}" var="view" varStatus="status">
  <dt>
    ${view.displayLabel}
  </dt>
  <dd>
    <select disabled="disabled" style="width: 250px" size="2" multiple="multiple" name="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}">
      <c:if test="${!status.first}">
        <option value="DEFAULT">All</option>
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
          <input type="text" class="manualEntry" id="${view.typeName}_manualEntry" size="6" maxlength="6" /><input type="button" class="manualSearch" id="${view.typeName}_manualSearch" value="Search" />
        </c:otherwise>
      </c:choose>
  </dd>
  </c:forEach>
  </dl>
  Best Fit:
  <dl style="height: 100px;">
    <dt id="bestFitName">
    </dt>
    <dd id="bestFitNameValue">
    </dd>
    <dt id="bestFitId">
    </dt>
    <dd id="bestFitIdValue">
    </dd>
  </dl>
</div>