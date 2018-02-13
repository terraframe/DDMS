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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="View_All_Malaria_Seasons" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:table var="item" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.general.MalariaSeasonController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="seasonLabel">
      <mjl:row>${item.seasonLabel.value}</mjl:row>      
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="startDate">
      <mjl:row>
        <fmt:formatDate value="${item.startDate}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="endDate">
      <mjl:row>
        <fmt:formatDate value="${item.endDate}" pattern="${dateFormatPattern}"  />
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.MalariaSeason.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mdss:localize key="Edit" var="Localized_Edit" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.general.MalariaSeasonController.edit.mojo" name="dss.vector.solutions.MalariaSeason.form.edit.button" />
        </mjl:form>
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
<mjl:commandLink action="dss.vector.solutions.general.MalariaSeasonController.newInstance.mojo" name="MalariaSeasonController.newInstance">
<mdss:localize key="Create_a_new_Transmission_Season" /> 
</mjl:commandLink>