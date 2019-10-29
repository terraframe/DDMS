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
<%@page import="com.runwaysdk.constants.ClientConstants"%>
<%@page import="com.runwaysdk.constants.ClientRequestIF"%>
<%@page import="com.runwaysdk.system.metadata.MdWebFormDTO"%>
<%@page import="dss.vector.solutions.form.MdFormAdminController"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.runwaysdk.web.json.JSONController"%>

<mjl:form name="MdFormAdmin.form.name" id="MdWebFormAdmin.form.id" method="POST">
<mjl:component param="form" item="${form}"/>
<mjl:input value="${form.id}" type="hidden" param="id" />
	<ul class="form-row">
	  <%@ include file="form.jsp"%>
	</ul>
	
	<div class="form-action-row" id="formActionRow">
	  <mdss:localize key="Create" var="Localized_Create" />
	  <mjl:command value="${Localized_Create}" action="dss.vector.solutions.form.MdFormAdminController.create.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.create.button"/>
	  <mdss:localize key="Cancel" var="Localized_Cancel" />
	  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.form.MdFormAdminController.cancel.mojo" name="dss.vector.solutions.form.MdFormAdminController.form.cancel.button" />
	</div>      
</mjl:form>