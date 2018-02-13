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
<c:set var="page_title" value="View_All_Properties" scope="request" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mdss:localize key="Edit" var="Localized_Edit" />

<mjl:table var="item" query="${local}" classes="displayTable" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.PropertyController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="propertyLabel">
      <mjl:header />
      <mjl:row>
        ${item.displayLabel}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="propertyDescription">
      <mjl:header />
      <mjl:row>
        ${item.description}
      </mjl:row>
    </mjl:attributeColumn>

    <mjl:attributeColumn attributeName="propertyValue">
      <mjl:header />
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>

      </mjl:header>
      <mjl:row>
      <c:if test="${item.editable}">
        <mjl:form name="dss.vector.solutions.LocalProperty.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.PropertyController.localEdit.mojo" name="dss.vector.solutions.LocalProperty.form.edit.button" classes="submitButton" />
        </mjl:form>
      </c:if>
      <c:if test="${!item.editable}">
        <button type="button" disabled="disabled">${Localized_Edit}</button>
      </c:if>
      </mjl:row>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>

<br />

<c:if test="${query2 != null}">
<mjl:table classes="displayTable" var="item" query="${query2}" even="evenRow" odd="oddRow">
  <mjl:context action="dss.vector.solutions.DefaultGeoEntityController.viewPage.mojo" />
  <mjl:columns>
  
    <mjl:freeColumn>
      <mjl:header>
        <mdss:localize key="Display_Label" />
      </mjl:header>
      <mjl:row>
        <mdss:localize key="Default_Geo_Root" />
      </mjl:row>
      
    </mjl:freeColumn>
  
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.displayString}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:form name="dss.vector.solutions.Property.form.name" id="${item.id}" method="POST">
          <mjl:input value="${item.id}" type="hidden" param="id" />
          <mjl:command value="${Localized_Edit}" action="dss.vector.solutions.DefaultGeoEntityController.edit.mojo" name="dss.vector.solutions.DefaultGeoEntityController.edit.btn" classes="submitButton" />
        </mjl:form>        
      </mjl:row>
      <mjl:footer>
        
      </mjl:footer>
    </mjl:freeColumn>
  </mjl:columns>
</mjl:table>
</c:if>
<br />