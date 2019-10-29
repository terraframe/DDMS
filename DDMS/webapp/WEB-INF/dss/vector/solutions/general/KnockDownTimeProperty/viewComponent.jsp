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
<c:set var="page_title" value="Found_Knock_Down_Property"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>  
  <mjl:form name="dss.vector.solutions.general.KnockDownTimeProperty.form.name" id="dss.vector.solutions.general.KnockDownTimeProperty.form.id" method="POST">
    <mjl:input value="${item.id}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="insecticide">
        ${item.insecticide.displayLabel}      
      </mjl:dt>
      <mjl:dt attribute="lowerPercent"> ${item.lowerPercent} </mjl:dt>
      <mjl:dt attribute="lowerTime"> ${item.lowerTime} </mjl:dt>
      <mjl:dt attribute="upperPercent"> ${item.upperPercent} </mjl:dt>
      <mjl:dt attribute="upperTime"> ${item.upperTime} </mjl:dt>  
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.general.KnockDownTimePropertyController.edit.mojo" name="dss.vector.solutions.general.KnockDownTimeProperty.form.edit.button" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.general.KnockDownTimePropertyController.search.mojo" name="search.link">
  <mdss:localize key="Search" />
</mjl:commandLink>
