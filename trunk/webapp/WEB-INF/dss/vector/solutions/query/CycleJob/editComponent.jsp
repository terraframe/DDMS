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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld"%>
<style>
<!--
  .job-dl {
    border: none;
    padding: 1em;
    background: none;
    min-width: 0px;
    max-width: 300px;
    margin-left: 0px;
    margin-bottom: 5px;
  }
-->
</style>
<dl class="job-dl">
	<mjl:form id="job-form" name="job-name" method="POST">
		<%@include file="form.jsp"%>
	</mjl:form>
</dl>
