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

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>


<c:set var="page_title" value="Enter_Collection" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="form.name" id="form.id" method="GET">
  <dl>
    <dt> 
      ${item.collectionMd.displayLabel}
    </dt>
    <dd>
      <mjl:input id="collectionInput" param="collectionInput" type="text" />
      <mjl:input id="collectionId" param="collectionId" type="hidden" />        
    </dd>
    <mdss:localize key="Search" var="Localized_Search" />
    <mjl:command name="search" action="dss.vector.solutions.entomology.AssayController.getInfectionAssays.mojo" value="${Localized_Search}" />
  </dl>
  
</mjl:form>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   

    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>

<h1><mdss:localize key="Import_Export_Infection_Assays"/></h1>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.InfectionAssayExcelView" name="excelType"/>
</jsp:include>

<h1><mdss:localize key="Import_Export_Pooled_Infection_Assays"/></h1>
<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.PooledInfectionAssayExcelView" name="excelType"/>
</jsp:include>