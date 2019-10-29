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
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table var="item" query="${query}">
  <mjl:context action="dss.vector.solutions.irs.TeamSprayController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="sprayTeam">
      <mjl:header>
        Team
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="target">
      <mjl:header>
        Target
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="teamSprayWeek">
      <mjl:header>
        Team Spray Week
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="sprayData">
      <mjl:header>
        Spray Data
      </mjl:header>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink action="dss.vector.solutions.irs.TeamSprayController.view.mojo" name="view.link">
          <mdss:localize key="View" />
          <mjl:property value="${item.id}" name="id" />
        </mjl:commandLink>
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
  <mjl:pagination>
    <mjl:page />
  </mjl:pagination>
</mjl:table>
<br />
<mjl:commandLink action="dss.vector.solutions.irs.TeamSprayController.newInstance.mojo" name="TeamSprayController.newInstance">
<mdss:localize key="Create_a_new_Operator_Spray" />
</mjl:commandLink>
