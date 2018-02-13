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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.LocalPropertyDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO"%>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input type="hidden" param="household" value="${item.household.id}"/>
  <mjl:dt attribute="personId">
    <mjl:input param="personId" type="text" id="personId"/><a href="#" id="getUniqueId"><mdss:localize key="Get_Unique_Id"/></a>
  </mjl:dt>  
  <mjl:dt attribute="headOfHousehold">
    <mdss:mo param="headOfHousehold" value="${headOfHousehold}"/>
  </mjl:dt>
  <mjl:dt attribute="dob">
    <mjl:input type="text" param="dob" id="dob" classes="DatePick NoFuture" />
  </mjl:dt>
  <mjl:dt attribute="age">
    <mjl:input type="text" param="age" id="age" size="3" maxlength="3" value=""/>
  </mjl:dt>
  <mjl:dt attribute="sex">
    <mdss:mo param="sex" value="${sex}"/>
  </mjl:dt>
  <mjl:dt attribute="pregnant">
    <mjl:boolean param="pregnant" />
  </mjl:dt>
  <mjl:dt attribute="immuneCompromised">
    <mdss:mo param="immuneCompromised" value="${immuneCompromised}"/>
  </mjl:dt>
  <mjl:dt attribute="sleptUnderNet">
    <mjl:select param="sleptUnderNet" items="${sleptUnderNet}" var="current" valueAttribute="concreteId" includeBlank="true">
      <mjl:option>
        ${current.netId}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="haemoglobinMeasured">
    <mjl:select param="haemoglobinMeasured" items="${haemoglobinMeasured}" var="current" valueAttribute="enumName" id="haemoglobinMeasured">
      <mjl:option selected="${mjl:contains(item.haemoglobinMeasuredEnumNames, current.enumName) ? 'selected' : 'false'}" id="haemoglobinMeasured.${current.enumName}">
        ${item.haemoglobinMeasuredMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <div class="haemoglobin">
    <mjl:dt attribute="haemoglobin">
      <mjl:input param="haemoglobin" type="text" id="haemoglobin"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="anaemiaTreatment">
    <mdss:mo param="anaemiaTreatment" value="${anaemiaTreatment}"/>
  </mjl:dt>
  <mjl:dt attribute="iron">
    <mjl:boolean param="iron" />
  </mjl:dt>  
  <mjl:dt attribute="performedRDT">
    <mjl:select param="performedRDT" items="${performedRDT}" var="current" valueAttribute="enumName" id="performedRDT">
      <mjl:option selected="${mjl:contains(item.performedRDTEnumNames, current.enumName) ? 'selected' : 'false'}" id="performedRDT.${current.enumName}">
        ${item.performedRDTMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <div class="rdtResult">
    <mjl:dt attribute="rdtResult">
      <mjl:boolean param="rdtResult" id="rdtResult" />
    </mjl:dt>  
  </div>
  <div class="rdtDetail">
    <mjl:dt attribute="rdtDetail">
      <mdss:mo param="rdtDetail" value="${rdtDetail}"/>
    </mjl:dt>
  </div>
  <div class="rdtTreatment">  
    <mjl:dt attribute="rdtTreatment">
      <mdss:mo param="rdtTreatment" value="${rdtTreatment}"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="performedBloodslide">
    <mjl:boolean param="performedBloodslide" id="performedBloodslide" />
  </mjl:dt>
  <div class="bloodslideReason">
    <mjl:dt attribute="bloodslideReason">
      <mdss:mo param="bloodslideReason" value="${bloodslideReason}"/>
    </mjl:dt>
  </div>
  <div class="bloodslideResult">
    <mjl:dt attribute="bloodslideResult">
      <mjl:boolean param="bloodslideResult" id="bloodslideResult" />
    </mjl:dt>    
  </div>
  <div class="bloodslideDetail">
    <mjl:dt attribute="bloodslideDetail">
      <mdss:mo param="bloodslideDetail" value="${bloodslideDetail}"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="fever">
    <mjl:select param="fever" items="${fever}" var="current" valueAttribute="enumName" id="fever">
      <mjl:option selected="${mjl:contains(item.feverEnumNames, current.enumName) ? 'selected' : 'false'}" id="fever.${current.enumName}">
        ${item.feverMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <div class="malaria">
    <mjl:dt attribute="malaria">
      <mjl:select param="malaria" items="${malaria}" var="current" valueAttribute="enumName" id="malaria">
        <mjl:option selected="${mjl:contains(item.malariaEnumNames, current.enumName) ? 'selected' : 'false'}" id="malaria.${current.enumName}">
          ${item.malariaMd.enumItems[current.enumName]}
        </mjl:option>
      </mjl:select>
    </mjl:dt>
  </div>
  <div class="malariaConformationTechnique">
    <mjl:dt attribute="malariaConformationTechnique">
      <mdss:mo param="malariaConformationTechnique" value="${malariaConformationTechnique}"/>
    </mjl:dt>  
  </div>
  <div class="locations">
    <mjl:dt attribute="displayLocations">
      <mdss:multimo param="locations" id="locations" browserAttribute="displayLocations" value="${locations}" />
    </mjl:dt>  
  </div>
  <div class="treatments">
    <mjl:dt attribute="displayTreatments">
      <mdss:multimo param="treatments" id="treatments" browserAttribute="displayTreatments" value="${treatments}" />
    </mjl:dt>
  </div>
  <div class="payment">
    <mjl:dt attribute="payment">
      <mdss:mo param="payment" value="${payment}"/>
    </mjl:dt>
  </div>
</mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{LocalPropertyDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var haemoglobin = new MDSS.HiddenInputElement({element:'haemoglobin'});
    var rdtResult = new MDSS.HiddenBooleanElement({element:'rdtResult'});
    var rdtDetail = new MDSS.HiddenInputElement({element:'rdtDetail'});
    var rdtTreatment = new MDSS.HiddenInputElement({element:'rdtTreatment'});
    var bloodslideReason = new MDSS.HiddenInputElement({element:'bloodslideReason'});
    var bloodslideResult = new MDSS.HiddenBooleanElement({element:'bloodslideResult'});
    var bloodslideDetail = new MDSS.HiddenInputElement({element:'bloodslideDetail'});
    var malaria = new MDSS.HiddenSelectElement({element:'malaria'});
    var malariaConformationTechnique = new MDSS.HiddenInputElement({element:'malariaConformationTechnique'});
    var locations = new MDSS.HiddenMultiTermElement({element:'locations'});
    var treatments = new MDSS.HiddenMultiTermElement({element:'treatments'});
    var payment = new MDSS.HiddenInputElement({element:'payment'});
        
    MDSS.ElementHandler.setupSelectHandler('haemoglobinMeasured.YES', new Array('haemoglobinMeasured', 'haemoglobinMeasured.NO', 'haemoglobinMeasured.REFUSED'), [haemoglobin]);    
    MDSS.ElementHandler.setupSelectHandler('performedRDT.YES', new Array('performedRDT', 'performedRDT.NO', 'performedRDT.DONT_KNOW'), [rdtResult, rdtDetail, rdtTreatment]);    
    MDSS.ElementHandler.setupBooleanHandler('performedBloodslide.negative', 'performedBloodslide.positive', [bloodslideReason]);
    MDSS.ElementHandler.setupBooleanHandler('performedBloodslide.positive', 'performedBloodslide.negative', [bloodslideResult, bloodslideDetail]);
    
    var feverHandler = MDSS.ElementHandler.setupSelectHandler('fever.YES', new Array('fever', 'fever.NO', 'fever.DONT_KNOW'), [malaria]);
    var malariaHandler = MDSS.ElementHandler.setupSelectHandler('malaria.YES', new Array('malaria', 'malaria.NO', 'malaria.DONT_KNOW'), [malariaConformationTechnique, locations, treatments, payment]);

    feverHandler.addListener(malariaHandler);

    //**********************************************************
    // SETUP UNIQUE ID GENERATOR
    //**********************************************************    
    YAHOO.util.Event.on('getUniqueId', 'click', function(e, obj){
      var request = new MDSS.Request({
          onSend: function(){},
          onComplete: function(){},
          onSuccess : function(result){
          document.getElementById('personId').value = result;
        }
      });
      Mojo.$.dss.vector.solutions.LocalProperty.getNextId(request);
    });
  })
})();
</script>
