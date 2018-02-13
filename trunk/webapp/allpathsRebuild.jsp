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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jawr.net/tags" prefix="jwr" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<c:set var="page_title" value="allpaths_rebuild_title"  scope="request"/>

<mdss:localize key="allpaths_rebuild_message" var="allpaths_rebuild_message" />
<mdss:localize key="allpaths_rebuild_title" var="allpaths_rebuild_title" />

<jsp:include page="/WEB-INF/templates/banner.jsp" />
<div class="pageContent">
<div class="pageTitle">${allpaths_rebuild_title}</div>
  <div class="alert alertbox">
  <p>
  ${allpaths_rebuild_message}
  </p>
  </div>
</div>

<jsp:include page="/WEB-INF/templates/footer.jsp" />