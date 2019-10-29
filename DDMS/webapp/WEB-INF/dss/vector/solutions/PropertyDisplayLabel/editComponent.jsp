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
<c:set scope="request" var="page_title" value="Edit_PropertyDisplayLabel" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.PropertyDisplayLabel.form.id" name="dss.vector.solutions.PropertyDisplayLabel.form.name" method="POST">
    <%@include file="form.jsp" %>
    <mjl:command name="dss.vector.solutions.PropertyDisplayLabel.form.update.button" value="Update" action="dss.vector.solutions.PropertyDisplayLabelController.update.mojo" />
    <mjl:command name="dss.vector.solutions.PropertyDisplayLabel.form.delete.button" value="Delete" action="dss.vector.solutions.PropertyDisplayLabelController.delete.mojo" />
    <mjl:command name="dss.vector.solutions.PropertyDisplayLabel.form.cancel.button" value="Cancel" action="dss.vector.solutions.PropertyDisplayLabelController.cancel.mojo" />
  </mjl:form>
</dl>
