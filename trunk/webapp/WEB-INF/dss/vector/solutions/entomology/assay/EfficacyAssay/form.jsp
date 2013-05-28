<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayDTO"%>
<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssay"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="dss.vector.solutions.entomology.assay.EfficacyAssayViewDTO"%>



<%=Halp.loadTypes(Arrays.asList(new String[]{EfficacyAssayDTO.CLASS}))%>

<script type="text/javascript">
Mojo.Meta.newClass("MDSS.EfficacyAssayValidation", {

  IsSingleton : true,
  
  Instance : {
  
    initialize : function()
    {
      this._inputs = [];
      this._entityId = null;
    },
  
    setInputs : function(inputs){
      this._inputs = inputs;
    },
  
    setEntityId : function(entityId)
    {
      this._entityId = entityId;
    },
  
    attach : function(){
      // attach a blur listener to each input in the validation group
    
      for(var i=0;i <this._inputs.length; i++)
      {
        var input = this._inputs[i];
        if(!YAHOO.util.Dom.hasClass(input))
        {
          YAHOO.util.Event.addListener(input, 'blur', this.preValidateCheck, null, this);
        }
      }    
    },  
  
    preValidateCheck : function(e)
    {  
      console.log(this);
    
      // if all inputs have values then invoke the validate method
      // on the target input
    
      if(!Mojo.Util.isString(this._entityId) || Mojo.Util.trim(this._entityId).length === 0)
      {
        return;
      }
    
      for(var i=0; i<this._inputs.length; i++)
      {
        var input = this._inputs[i];
        var value = input.value;
        if(!Mojo.Util.isString(value) || Mojo.Util.trim(value).length === 0)
        {
          return; // input needs a value
        }
      }
  
      var testDate = MDSS.Calendar.parseDate(this._inputs[0].value);
      var controlTestMortality = MDSS.parseNumber(this._inputs[1].value);
      
      var request = new MDSS.Request({
        onSend: function(){},
        onComplete: function(){},          
        onSuccess: function(valid) {       
          if(!valid)
          {
            alert(MDSS.localize('changing_control_mortality'));
          }
        }
      });
       
      Mojo.$.dss.vector.solutions.entomology.assay.EfficacyAssay.checkControlTestMortality(request, this._entityId, testDate, controlTestMortality);          
    },
    
    handleEvent : function (e)
    {
      if(e instanceof MDSS.Event && e.getType() == MDSS.Event.AFTER_SELECTION)
      {
        var entityId = null;
        var geoEntity = e.getValue().selected;
          
        if(geoEntity != null)
        {
          entityId = geoEntity.getGeoId();
        }
      
        this.setEntityId(entityId);
        this.preValidateCheck(null);
      }    
    }    
  },
});
</script>    

    <mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:dt attribute="geoId">
        <mdss:geo param="geoId" value="${geoId}" concrete="false" filter="${surface}" id="geoId" classes="geoField" listener="MDSS.EfficacyAssayValidation.getInstance()"  />      
      </mjl:dt>
      <mjl:dt attribute="surfaceType">
        <mdss:mo param="surfaceType" value="${surfaceType}"/>
      </mjl:dt>   
      <mjl:dt attribute="testDate">
        <mjl:input type="text" param="testDate" classes="DatePick NoFuture" id="testDate" />
      </mjl:dt>
      <mjl:dt attribute="controlTestMortality">
        <fmt:formatNumber minFractionDigits="2" var="formatControlTestMortality" value="${item.controlTestMortality}" />
        <mjl:input type="text" param="controlTestMortality" value="${formatControlTestMortality}" id="controlTestMortality" />
      </mjl:dt>      
      <mjl:dt attribute="testMethod">
        <mdss:mo param="testMethod" value="${testMethod}"/>
      </mjl:dt>      
      <mjl:dt attribute="specie">
        <mdss:mo param="specie" value="${specie}"/>
      </mjl:dt>      
      <mjl:dt attribute="colonyName">
        <mjl:input type="text" param="colonyName" />
      </mjl:dt>
      <mjl:dt attribute="ageRange">
        <mjl:struct param="ageRange">
          <mjl:dt attribute="startPoint" type="text"  />
          <mjl:dt attribute="endPoint" type="text"  />
        </mjl:struct>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <mdss:mo param="sex" value="${sex}"/>
      </mjl:dt>      
      <mjl:dt attribute="gravid">
        <mjl:input type="text" param="gravid" />
      </mjl:dt>
      <mjl:dt attribute="fed">
        <mjl:input type="text" param="fed" />
      </mjl:dt>
      <mjl:dt attribute="insecticideBrand"> 
        <mjl:select var="current" valueAttribute="insecticdeId" items="${brands}" param="insecticideBrand" >
         <mjl:option>
           ${current.productName.termDisplayLabel.value}
         </mjl:option>
        </mjl:select>
      </mjl:dt>
      <mjl:dt attribute="timeOnSurface" >
        <mjl:input type="text" param="timeOnSurface" />
      </mjl:dt>
      <mjl:dt attribute="surfacePostion">
        <mdss:mo param="surfacePostion" value="${surfacePostion}"/>
      </mjl:dt>      
      <mjl:dt attribute="exposureTime">
        <mjl:input type="text" param="exposureTime" />
      </mjl:dt>
      <mjl:dt attribute="holdingTime">
        <mjl:input type="text" param="holdingTime" />
      </mjl:dt>
      <mjl:dt attribute="quantityTested">
        <mjl:input type="text" param="quantityTested" />
      </mjl:dt>
      <mjl:dt attribute="quantityDead">
        <mjl:input type="text" param="quantityDead" />
      </mjl:dt>      
    </mjl:component>

  <script type="text/javascript">
  
    (function(){
    
      // Change this to grab the elements
    var input2 = document.getElementById('testDate');
    var input3 = document.getElementById('controlTestMortality');
    var inputs = [input2, input3];
    
    var instance = MDSS.EfficacyAssayValidation.getInstance();
    instance.setInputs(inputs);
    instance.setEntityId(document.getElementById('geoId').value);
    instance.attach();
    
    })();
  
  </script>    
    