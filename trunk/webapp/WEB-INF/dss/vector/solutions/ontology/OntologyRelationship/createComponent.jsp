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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="dss.vector.solutions.ontology.OntologyRelationship.form.name" id="dss.vector.solutions.ontology.OntologyRelationship.form.id" method="POST">
    <%@include file="form.jsp" %>
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.ontology.OntologyRelationshipController.create.mojo" name="dss.vector.solutions.ontology.OntologyRelationship.form.create.button" />
  </mjl:form>
</dl>
