<%@page import="dss.vector.solutions.entomology.MosquitoCollectionDTO"%>
<%@page import="dss.vector.solutions.geo.GeoEntityViewDTO"%>
<%@page import="dss.vector.solutions.entomology.MosquitoCollectionViewDTO"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
    
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>

<script type="text/javascript">
Mojo.Meta.newClass("MDSS.ValidationBridge", {

  IsSingleton : true,
  
  Instance : {
  
    initialize : function()
    {
    },
    
    setHandler : function(handler) {
      this._handler = handler;
    },

    handleEvent : function(evt){      
      if(this._handler != null)
      {
        if(evt instanceof dss.vector.solutions.ontology.TermSelectedEvent)
        {        
          if(!evt.getOnOpen())
          {
            this._handler.validate(evt);          
          }
        }
        else
        {        
          this._handler.validate(evt);
        }
      }
    }    
  },
});
</script>    


<mjl:input type="hidden" param="id" id="id" value="${item.id}" />

<mjl:component item="${item}" param="dto">
  <mjl:input id="rootAssay" type="hidden" param="rootAssay" classes="component" value="${item.rootAssay}"  />
  <mjl:dt attribute="collection">
    <mjl:input id="collectionInput" type="text" value="${collection != null ? collection.collectionId : ''}"/>
    <mjl:input id="collection" param="collection" type="hidden" value="${collection != null ? collection.concreteId : ''}"  classes="component" />    
  </mjl:dt>
  <mjl:dt attribute="uniqueAssayId">
    <mjl:input type="text" param="uniqueAssayId" classes="component" id="uniqueAssayId" />
  </mjl:dt>
  <mjl:dt attribute="testDate">
    <mjl:input type="text" param="testDate" id="testDate" classes="DatePick NoFuture component replicate" />
  </mjl:dt>
  <mjl:dt attribute="testMethod">
    <mdss:mo param="testMethod" value="${testMethod}"  classes="component" id="testMethod" listener="MDSS.ValidationBridge.getInstance()"  />
  </mjl:dt>  
  <mjl:dt attribute="generation">
    <mdss:mo param="generation" value="${generation}" classes="component" id="generation" listener="MDSS.ValidationBridge.getInstance()"/>
  </mjl:dt>  
  <mjl:dt attribute="isofemale">
    <mjl:boolean param="isofemale" id="isofemale" classes="replicate" />
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mdss:mo param="sex" value="${sex}" classes="component" id="sex" listener="MDSS.ValidationBridge.getInstance()"/>
  </mjl:dt>  
  <mjl:dt attribute="specie">
    <mdss:mo param="specie" value="${specie}" classes="component" id="specie" listener="MDSS.ValidationBridge.getInstance()"/>
  </mjl:dt>  
  <mjl:dt attribute="identificationMethod">
    <mdss:mo param="identificationMethod" value="${identificationMethod}" classes="component" id="identificationMethod" listener="MDSS.ValidationBridge.getInstance()"/>
  </mjl:dt>  
  <mjl:dt attribute="ageRange">
    <mjl:struct param="ageRange">
      <mjl:dt attribute="startPoint" type="text" id="startPoint" classes="replicate" />
      <mjl:dt attribute="endPoint" type="text" id="endPoint"  classes="replicate"/>
    </mjl:struct>
  </mjl:dt>
  <mjl:dt attribute="fed">
    <mjl:input type="text" param="fed"  classes="component" id="fed"/>
  </mjl:dt>
  <mjl:dt attribute="gravid">
    <mjl:input type="text" param="gravid" classes="component" id="gravid" />
  </mjl:dt>
  <mjl:dt attribute="exposureTime">
    <mjl:input type="text" param="exposureTime" classes="component replicate" id="exposureTime" />
  </mjl:dt>
  <mjl:dt attribute="holdingTime">
    <mjl:input type="text" param="holdingTime" classes="component replicate" id="holdingTime" />
  </mjl:dt>
  <mjl:dt attribute="insecticide">
    <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide" classes="component replicate" id="insecticide">
      <mjl:option>
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
    <a href="dss.vector.solutions.general.InsecticideController.viewAll.mojo"><mdss:localize key="Manage_Insecticides" /></a>
  </mjl:dt>
  <mjl:dt attribute="quantityTested">
    <mjl:input type="text" param="quantityTested" classes="component replicate" id="quantityTested" />
  </mjl:dt>

  <mjl:dt attribute="quantityDead">
    <mjl:input type="text" param="quantityDead" classes="component" id="quantityDead" />
  </mjl:dt>
  
  <mjl:dt attribute="controlTestNumberExposed">
    <mjl:input type="text" param="controlTestNumberExposed" classes="component replicate" id="controlTestNumberExposed" />
  </mjl:dt>
  
  <mjl:dt attribute="controlTestNumberDead">
    <mjl:input type="text" param="controlTestNumberDead" classes="component replicate" id="controlTestNumberDead" />
  </mjl:dt>

  <mjl:dt attribute="kd50">
    <fmt:formatNumber minFractionDigits="2" var="formatKd50" value="${item.kd50}" />
    <mjl:input type="text" param="kd50" value="${formatKd50}" id="kd50" classes="component"/>
  </mjl:dt>

  <mjl:dt attribute="kd95">
    <fmt:formatNumber minFractionDigits="2" var="formatKd95" value="${item.kd95}" />
    <mjl:input type="text" param="kd95" value="${formatKd95}" id="kd95" classes="component" />
  </mjl:dt>
</mjl:component>
<dd>
  <div id="intervals"></div>
</dd>

<%=Halp.loadTypes(Arrays.asList(new String[]{MosquitoCollectionViewDTO.CLASS, GeoEntityViewDTO.CLASS}))%>

<script type="text/javascript">  
(function(){
  YAHOO.util.Event.onDOMReady(function(){   
    MDSS.collectionSearch({search:'collectionInput', concrete:'collection', handler:MDSS.ValidationBridge.getInstance(), type:'<%=MosquitoCollectionDTO.CLASS%>'});
  })
})();

</script>    

