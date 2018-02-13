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
<c:set var="page_title" scope="request" value="View_All_TargetFieldGeoEntityBinding" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:table even="evenRow" var="item" query="${query}" classes="displayTable" odd="oddRow">
  <mjl:context action="dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBindingController.viewPage.mojo" />
  <mjl:columns>
    <mjl:attributeColumn attributeName="geoEntity">
      <mjl:row>
        ${item.geoEntity.geoId}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="columnLabel">
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="target">
      <mjl:row>
        ${item.target.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:attributeColumn attributeName="targetAttribute">
      <mjl:row>
        ${item.targetAttribute.keyName}
      </mjl:row>
    </mjl:attributeColumn>
    <mjl:freeColumn>
      <mjl:header>
        
      </mjl:header>
      <mjl:row>
        <mjl:commandLink name="view.link" action="dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBindingController.view.mojo">
          <mdss:localize key="View" />
          <mjl:property name="id" value="${item.id}" />
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
<mjl:commandLink name="TargetFieldGeoEntityBindingController.newInstance" action="dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityBindingController.newInstance.mojo">
  <mdss:localize key="Create_a_new_Target_Field_Geo_Entity_Binding" />
</mjl:commandLink>
