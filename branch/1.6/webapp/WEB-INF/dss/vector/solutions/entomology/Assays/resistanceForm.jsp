<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>

<mjl:form name="form.name" id="form.id" method="GET">
  <dl>
    <dt> 
      <label>${diagnostic.collectionMd.displayLabel}</label>
    </dt>
    <dd>
      <mjl:input id="collectionInput" param="collectionInput" type="text" />
      <mjl:input id="collectionId" param="collectionId" type="hidden" />        
      <mdss:localize key="Go" var="Localized_Go" />        
      <mjl:command name="search" action="dss.vector.solutions.entomology.AssayController.getResistanceAssays.mojo" value="${Localized_Go}" />
    </dd>
  </dl>  
</mjl:form>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">

(function(){
  YAHOO.util.Event.onDOMReady(function(){
    MDSS.collectionSearch({search:'collectionInput', concrete:'collectionId', type:'<%=MosquitoCollectionDTO.CLASS%>'});
  });
})();
        
</script>
    