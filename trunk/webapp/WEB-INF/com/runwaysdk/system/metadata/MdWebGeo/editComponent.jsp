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
<c:set scope="request" var="page_title" value="Edit_MdWebGeo" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<h2 class="fieldTitle">${item.md.displayLabel}</h2>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebGeo.form.id" name="com.runwaysdk.system.metadata.MdWebGeo.form.name" method="POST">
<dl>
    <%@include file="form.jsp" %>
</dl>
    <mdss:localize var="Update_Localize" key="Update" />
    <button name="dss.vector.solutions.form.MdFormAdminController.updateMdField.button" id="2fl4v042j9d61nw2aqxgeotu7wf5pgkl" value="${Update_Localize}">
${Update_Localize}</button>
    <mdss:localize var="Cancel_Localize" key="Cancel" />
    <mjl:command name="dss.vector.solutions.form.MdFormAdminController.cancelMdField.button" value="${Cancel_Localize}" action="dss.vector.solutions.form.MdFormAdminController.cancelMdField.mojo" />
</mjl:form>


<script type="text/javascript">
var geoClick = function(){
  var params = Mojo.Util.collectFormValues('com.runwaysdk.system.metadata.MdWebGeo.form.id');
  
  if (params["geoField.hierarchy"] != null)
  {
    var hierarchy = params["geoField.hierarchy"][0];
    
    if (hierarchy === "spray")
    {
      params["geoField.isPoliticalHierarchy"] = "false";
      params["geoField.isSprayHierarchy"] = "true";
      params["geoField.isPopulationHierarchy"] = "false";
      params["geoField.isUrbanHierarchy"] = "false";
    }
    else if (hierarchy === "political")
    {
      params["geoField.isPoliticalHierarchy"] = "true";
      params["geoField.isSprayHierarchy"] = "false";
      params["geoField.isPopulationHierarchy"] = "false";
      params["geoField.isUrbanHierarchy"] = "false";
    }
    else if (hierarchy === "urban")
    {
      params["geoField.isPoliticalHierarchy"] = "false";
      params["geoField.isSprayHierarchy"] = "false";
      params["geoField.isPopulationHierarchy"] = "false";
      params["geoField.isUrbanHierarchy"] = "true";
    }
    else if (hierarchy === "population")
    {
      params["geoField.isPoliticalHierarchy"] = "false";
      params["geoField.isSprayHierarchy"] = "false";
      params["geoField.isPopulationHierarchy"] = "true";
      params["geoField.isUrbanHierarchy"] = "false";
    }
    else
    {
      params["geoField.isPoliticalHierarchy"] = "false";
      params["geoField.isSprayHierarchy"] = "false";
      params["geoField.isPopulationHierarchy"] = "false";
      params["geoField.isUrbanHierarchy"] = "false";
    }
    
    delete params["geoField.hierarchy"];
  }
  Mojo.$.dss.vector.solutions.form.MdFormAdminController._notifyUpdateGeoFieldListener(params, 'dss.vector.solutions.form.MdFormAdminController.updateGeoField.mojo', '2fl4v042j9d61nw2aqxgeotu7wf5pgkl');
  return false;
}
var but = document.getElementById('2fl4v042j9d61nw2aqxgeotu7wf5pgkl');
but.addEventListener('click', geoClick, false);
</script>