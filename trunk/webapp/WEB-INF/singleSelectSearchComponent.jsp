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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dss.vector.solutions.MDSSInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<div style="width: 695px; margin-top: 15px;" >
<div id="selectSearchComponent">
  <dl>
  <c:forEach items="${views}" var="view" varStatus="status">
  <dt id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_dt">
    ${view.displayLabel}
  </dt>
  <dd id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_dd">
    <div class="typeContainer" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_container">
      <c:if test="${!status.first}">
      <input type="text" class="ajaxSearch" value="" style="width: 225px" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_search" /><br />
      <div id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}_results" class="ajaxResults"></div>
    </c:if>
    <select disabled="disabled" class="typeSelect" style="width: 250px" name="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}" id="<%= MDSSInfo.GENERATED_GEO_PACKAGE %>.${view.typeName}">
    <c:if test="${!status.first}">
      <option value="DEFAULT"><f:message key="Select_One"/></option>
    </c:if>
    </select>
      <c:choose>
        <c:when test="${status.first}">
          <a href="#" id="treeOpener" style="margin-left: 20px"><img src="./imgs/icons/world.png" style="margin-right: 5px;"/><f:message key="Tree" /></a><br />
            <div class="yui-skin-sam" id="treeViewContainer" style="background-color:white">
              <div id="treeView"></div>
            </div>
        </c:when>
        <c:otherwise>
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