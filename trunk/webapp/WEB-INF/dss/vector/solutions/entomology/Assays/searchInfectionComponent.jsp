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
    <mjl:command name="search" action="dss.vector.solutions.entomology.AssayController.getInfectionAssays.mojo" value="Search"/>
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