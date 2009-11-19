<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@page import="dss.vector.solutions.PropertyDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.intervention.monitor.SurveyedPersonViewDTO"%>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input type="hidden" param="household" value="${item.household.id}"/>
  <mjl:dt attribute="personId">
    <mjl:input param="personId" type="text" id="personId"/><a href="#" id="getUniqueId"><fmt:message key="Get_Unique_Id"/></a>
  </mjl:dt>  
  <mjl:dt attribute="headOfHousehold">
    <span id="headOfHouseholdBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="headOfHouseholdDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${headOfHousehold != null}">
          ${headOfHousehold.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="headOfHousehold" param="headOfHousehold" value="${headOfHousehold != null ? headOfHousehold.id : ''}" type="hidden" />
  </mjl:dt>
  <mjl:dt attribute="dob">
    <mjl:input type="text" param="dob" id="dob" classes="DatePick NoFuture" />
  </mjl:dt>
  <mjl:dt attribute="age">
    <mjl:input type="text" param="age" id="age" size="3" maxlength="3" value=""/>
  </mjl:dt>
  <mjl:dt attribute="sex">
    <span id="sexBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="sexDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${sex != null}">
          ${sex.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="sex" param="sex" value="${sex != null ? sex.id : ''}" type="hidden" />
  </mjl:dt>
  <mjl:dt attribute="pregnant">
    <mjl:boolean param="pregnant" />
  </mjl:dt>
  <mjl:dt attribute="immuneCompromised">
    <span id="immuneCompromisedBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="immuneCompromisedDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${immuneCompromised != null}">
          ${immuneCompromised.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="immuneCompromised" param="immuneCompromised" value="${immuneCompromised != null ? immuneCompromised.id : ''}" type="hidden" />
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
    <span id="anaemiaTreatmentBtn" class="clickable browserLauncher">
      <fmt:message key="Browser" />
    </span>
    <div id="anaemiaTreatmentDisplay" class="ontologyDisplay">
      <c:choose>
        <c:when test="${anaemiaTreatment != null}">
          ${anaemiaTreatment.displayLabel}
        </c:when>
        <c:otherwise>
          <fmt:message key="no_value" />
        </c:otherwise>
      </c:choose>
    </div>
    <mjl:input id="anaemiaTreatment" param="anaemiaTreatment" value="${anaemiaTreatment != null ? anaemiaTreatment.id : ''}" type="hidden" />
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
      <span id="rdtDetailBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="rdtDetailDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${rdtDetail != null}">
            ${rdtDetail.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input id="rdtDetail" param="rdtDetail" value="${rdtDetail != null ? rdtDetail.id : ''}" type="hidden" />
    </mjl:dt>
  </div>
  <div class="rdtTreatment">  
    <mjl:dt attribute="rdtTreatment">
      <span id="rdtTreatmentBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="rdtTreatmentDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${rdtTreatment != null}">
            ${rdtTreatment.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input id="rdtTreatment" param="rdtTreatment" value="${rdtTreatment != null ? rdtTreatment.id : ''}" type="hidden" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="performedBloodslide">
    <mjl:boolean param="performedBloodslide" id="performedBloodslide" />
  </mjl:dt>
  <div class="bloodslideReason">
    <mjl:dt attribute="bloodslideReason">
      <span id="bloodslideReasonBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="bloodslideReasonDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${bloodslideReason != null}">
            ${bloodslideReason.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input id="bloodslideReason" param="bloodslideReason" value="${bloodslideReason != null ? bloodslideReason.id : ''}" type="hidden" />
    </mjl:dt>
  </div>
  <div class="bloodslideResult">
    <mjl:dt attribute="bloodslideResult">
      <mjl:boolean param="bloodslideResult" id="bloodslideResult" />
    </mjl:dt>    
  </div>
  <div class="bloodslideDetail">
    <mjl:dt attribute="bloodslideDetail">
      <span id="bloodslideDetailBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="bloodslideDetailDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${bloodslideDetail != null}">
            ${bloodslideDetail.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input id="bloodslideDetail" param="bloodslideDetail" value="${bloodslideDetail != null ? bloodslideDetail.id : ''}" type="hidden" />
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
      <span id="malariaConformationTechniqueBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="malariaConformationTechniqueDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${malariaConformationTechnique != null}">
            ${malariaConformationTechnique.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <mjl:input id="malariaConformationTechnique" param="malariaConformationTechnique" value="${malariaConformationTechnique != null ? malariaConformationTechnique.id : ''}" type="hidden" />
    </mjl:dt>  
  </div>
  <div class="locations">
    <mjl:dt attribute="displayLocations">
      <span class="clickable browserLauncher" id="locationsBtn"> <fmt:message key="Browser"/></span>
      <div id="locationsDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${fn:length(locations) > 0}">
            <c:forEach items="${locations}" var="current"> 
              ${current.displayLabel}
            </c:forEach>
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <div style="display:none;" id="locations">
        <mjl:components items="${locations}" param="locations" var="current" varStatus="status">
          <mjl:input type="hidden" param="componentId" classes="locations" value="${current.id}"/>
        </mjl:components>
      </div>
    </mjl:dt>  
  </div>
  <div class="treatments">
    <mjl:dt attribute="displayTreatments">
      <span class="clickable browserLauncher" id="treatmentsBtn"> <fmt:message key="Browser"/></span>
      <div id="treatmentsDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${fn:length(treatments) > 0}">
            <c:forEach items="${treatments}" var="current"> 
              ${current.displayLabel}
            </c:forEach>
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
      </div>
      <div style="display:none;" id="treatments">
        <mjl:components items="${treatments}" param="treatments" var="current" varStatus="status">
          <mjl:input type="hidden" param="componentId" classes="treatments" value="${current.id}"/>
        </mjl:components>
      </div>
    </mjl:dt>
  </div>
  <div class="payment">
    <mjl:dt attribute="payment">
      <span id="paymentBtn" class="clickable browserLauncher">
        <fmt:message key="Browser" />
      </span>
      <div id="paymentDisplay" class="ontologyDisplay">
        <c:choose>
          <c:when test="${payment != null}">
            ${payment.displayLabel}
          </c:when>
          <c:otherwise>
            <fmt:message key="no_value" />
          </c:otherwise>
        </c:choose>
       </div>
      <mjl:input id="payment" param="payment" value="${payment != null ? payment.id : ''}" type="hidden" />
    </mjl:dt>
  </div>
</mjl:component>

<%=Halp.loadTypes(Arrays.asList(new String[]{PropertyDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var haemoglobin = new MDSS.HiddenInputElement({element:'haemoglobin'});
    var rdtResult = new MDSS.HiddenRadioElement({element:'rdtResult'});
    var rdtDetail = new MDSS.HiddenInputElement({element:'rdtDetail'});
    var rdtTreatment = new MDSS.HiddenInputElement({element:'rdtTreatment'});
    var bloodslideReason = new MDSS.HiddenInputElement({element:'bloodslideReason'});
    var bloodslideResult = new MDSS.HiddenRadioElement({element:'bloodslideResult'});
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
    MDSS.ElementHandler.setupSelectHandler('fever.YES', new Array('fever', 'fever.NO', 'fever.DONT_KNOW'), [malaria, malariaConformationTechnique, locations, treatments, payment]);    
    MDSS.ElementHandler.setupSelectHandler('malaria.YES', new Array('malaria', 'malaria.NO', 'malaria.DONT_KNOW'), [malariaConformationTechnique, locations, treatments, payment]);    

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
      Mojo.$.dss.vector.solutions.Property.getNextId(request);
    });

    //**********************************************************
    // SETUP MO BROWSERS
    //**********************************************************
    var attributes = [
      {attributeName:'headOfHousehold'},
      {attributeName:'sex'},
      {attributeName:'immuneCompromised'},
      {attributeName:'anaemiaTreatment'},
      {attributeName:'rdtDetail'},
      {attributeName:'rdtTreatment'},
      {attributeName:'bloodslideReason'},
      {attributeName:'bloodslideDetail'},
      {attributeName:'malariaConformationTechnique'},
      {attributeName:'locations', browserField:'displayLocations', multipleSelect:true},
      {attributeName:'treatments', browserField:'displayTreatments', multipleSelect:true},
      {attributeName:'payment'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=SurveyedPersonViewDTO.CLASS%>", attributes);
  })
})();
</script>
