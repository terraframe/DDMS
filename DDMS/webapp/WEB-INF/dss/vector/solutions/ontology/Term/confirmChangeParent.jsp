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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<mjl:form name="confirmChangeParentForm" id="confirmChangeParentForm" method="POST">
<input type="hidden" id="childId" name="childId" value="${childId}" />
<input type="hidden" id="parentId" name="parentId" value="${parentId}" />
<div>
  <div class="modalAlertBox">
    <span>${message}</span>
  </div>
  <div class="modalAlertBox">
    <mdss:localize key="Choice_Yes" var="Localized_Choice_Yes" />
    <mjl:command value="${Localized_Choice_Yes}" action="dss.vector.solutions.ontology.TermController.doNotClone.mojo" name="dss.vector.solutions.ontology.TermController.doClone" />
    <mdss:localize key="Choice_No" var="Localized_Choice_No" />
    <mjl:command value="${Localized_Choice_No}" action="dss.vector.solutions.ontology.TermController.doClone.mojo" name="dss.vector.solutions.ontology.TermController.doNotClone" />
  </div>
</div>
</mjl:form>