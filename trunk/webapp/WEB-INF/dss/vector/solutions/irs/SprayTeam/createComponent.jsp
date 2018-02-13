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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/selectSearch.jsp"></jsp:include>

<c:set var="page_title" value="Spray_Team_Create" scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.irs.SprayTeam.form.name" id="dss.vector.solutions.irs.SprayTeam.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    <mdss:localize key="Create" var="Localized_Create" />
    <mjl:command value="${Localized_Create}" id="updateTeam" action="dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo" name="dss.vector.solutions.irs.SprayTeam.form.createAndAssign.button" />
  </dl>
</mjl:form>

<script type='text/javascript'>
        onTeam = document.getElementById('onTeam');
        notOnTeam = document.getElementById('notOnTeam');
        onOtherTeam = document.getElementById('onOtherTeam');
        teamRegex = '^ *[^[]';
        teamForm = document.getElementById('dss.vector.solutions.irs.SprayTeam.form.id');
        document.getElementById('updateTeam').onclick = function(){
          teamForm.action = 'dss.vector.solutions.irs.SprayTeamController.createAndAssign.mojo';
          Selectbox.selectAllOptions(onTeam);
          Selectbox.selectAllOptions(notOnTeam);
          teamForm.submit();
        }
</script>