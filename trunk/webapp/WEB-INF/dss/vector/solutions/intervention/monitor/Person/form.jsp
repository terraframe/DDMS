<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.PropertyDTO"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>


<%@page import="dss.vector.solutions.intervention.monitor.PersonViewDTO"%><jsp:include page="/WEB-INF/MOSearch.jsp" />

    <mjl:component item="${item}" param="dto">
      <mjl:input param="concreteId" type="hidden" value="${item.concreteId}" />
      <mjl:input param="household" type="hidden" value="${item.household.id}" />
      <mjl:dt attribute="personId">
        <mjl:input type="text" param="personId"  id="personId"/><a href="#" id="getUniqueId"> <fmt:message key="Get_Unique_Id"/> </a>
      </mjl:dt>
      <mjl:dt attribute="dob">
        <mjl:input type="text" param="dob" id="dob" classes="DatePick NoFuture" />
      </mjl:dt>
      <mjl:dt attribute="age">
        <mjl:input type="text" param="age" id="age" size="3" maxlength="3" value=""/>
      </mjl:dt>
      <mjl:dt attribute="sex">
        <span class="clickable" id="sexBtn"> <fmt:message key="Browser"/></span>
        <div id="sexDisplay" class="ontologyDisplay">
          <c:if test="${sex != null}">
            ${sex.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="sex" id="sex" value="${sex != null ? sex.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="pregnant">
        <mjl:boolean param="pregnant" />
      </mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        <mjl:boolean param="sleptUnderNet" />
      </mjl:dt>
      <mjl:dt attribute="haemoglobinMeasured">
        <mjl:boolean param="haemoglobinMeasured" />
      </mjl:dt>
      <mjl:dt attribute="haemoglobin">
        <mjl:input type="text" param="haemoglobin" />
      </mjl:dt>
      <mjl:dt attribute="anaemiaTreatment">
        <span class="clickable" id="anaemiaTreatmentBtn"> <fmt:message key="Browser"/></span>
        <div id="anaemiaTreatmentDisplay" class="ontologyDisplay">
          <c:if test="${anaemiaTreatment != null}">
            ${anaemiaTreatment.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="anaemiaTreatment" id="anaemiaTreatment" value="${anaemiaTreatment != null ? anaemiaTreatment.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="iron">
        <mjl:boolean param="iron" />
      </mjl:dt>
      <mjl:dt attribute="performedRDT">
        <span class="clickable" id="performedRDTBtn"> <fmt:message key="Browser"/></span>
        <div id="performedRDTDisplay" class="ontologyDisplay">
          <c:if test="${performedRDT != null}">
            ${performedRDT.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="performedRDT" id="performedRDT" value="${performedRDT != null ? performedRDT.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="bloodslide">
        <span class="clickable" id="bloodslideBtn"> <fmt:message key="Browser"/></span>
        <div id="bloodslideDisplay" class="ontologyDisplay">
          <c:if test="${bloodslide != null}">
            ${bloodslide.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="bloodslide" id="bloodslide" value="${bloodslide != null ? bloodslide.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="rDTResult">
        <mjl:checkboxGroup var="current" valueAttribute="enumName" items="${rDTResult}" param="rDTResult">
          <mjl:checkboxOption checked="${mjl:contains(item.RDTResultEnumNames, current.enumName) ? 'checked' : 'false'}">
            ${item.RDTResultMd.enumItems[current.enumName]}
          </mjl:checkboxOption>
        </mjl:checkboxGroup>
      </mjl:dt>
      <mjl:dt attribute="rdtTreatment">
        <span class="clickable" id="rdtTreatmentBtn"> <fmt:message key="Browser"/></span>
        <div id="rdtTreatmentDisplay" class="ontologyDisplay">
          <c:if test="${rdtTreatment != null}">
            ${rdtTreatment.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="rdtTreatment" id="rdtTreatment" value="${rdtTreatment != null ? rdtTreatment.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="fever">
        <span class="clickable" id="feverBtn"> <fmt:message key="Browser"/></span>
        <div id="feverDisplay" class="ontologyDisplay">
          <c:if test="${fever != null}">
            ${fever.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="fever" id="fever" value="${fever != null ? fever.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="feverTreatment">
        <span class="clickable" id="feverTreatmentBtn"> <fmt:message key="Browser"/></span>
        <div id="feverTreatmentDisplay" class="ontologyDisplay">
          <c:if test="${feverTreatment != null}">
            ${feverTreatment.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="feverTreatment" id="feverTreatment" value="${feverTreatment != null ? feverTreatment.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="malaria">
        <span class="clickable" id="malariaBtn"> <fmt:message key="Browser"/></span>
        <div id="malariaDisplay" class="ontologyDisplay">
          <c:if test="${malaria != null}">
            ${malaria.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="malaria" id="malaria" value="${malaria != null ? malaria.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="malariaTreatment">
        <span class="clickable" id="malariaTreatmentBtn"> <fmt:message key="Browser"/></span>
        <div id="malariaTreatmentDisplay" class="ontologyDisplay">
          <c:if test="${malariaTreatment != null}">
            ${malariaTreatment.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="malariaTreatment" id="malariaTreatment" value="${malariaTreatment != null ? malariaTreatment.id : ''}" />
      </mjl:dt>
      <mjl:dt attribute="payment">
        <span class="clickable" id="paymentBtn"> <fmt:message key="Browser"/></span>
        <div id="paymentDisplay" class="ontologyDisplay">
          <c:if test="${payment != null}">
            ${payment.displayLabel}
          </c:if>
        </div>
        <mjl:input type="hidden" param="payment" id="payment" value="${payment != null ? payment.id : ''}" />
      </mjl:dt>
    </mjl:component>

<%String[] types_to_load = {PropertyDTO.CLASS};%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>

<script type="text/javascript">


</script>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var age = document.getElementById('age');
    var dob = document.getElementById('dob');

    age.onclick=function()
    {
      //alert('test');
      if(dob.value.length > 0) age.disabled=true;
      else
        age.disabled=false;
    };

    dob.onclick=function()
    {
      //alert('test');
      if(age.value.length > 0) dob.disabled=true;
      else
        dob.disabled=false;
    };

    age.onblur=function()
    {
      if(age.value.length > 0) dob.disabled=true;
      else
        dob.disabled=false;
    };

    dob.onblur=function()
    {
      if(dob.value.length > 0) age.disabled=true;
      else
        age.disabled=false;
    };


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

    var attributes = [
      {attributeName:'anaemiaTreatment'},
      {attributeName:'bloodslide'},
      {attributeName:'fever'},
      {attributeName:'feverTreatment'},
      {attributeName:'malaria'},
      {attributeName:'malariaTreatment'},
      {attributeName:'payment'},
      {attributeName:'performedRDT'},
      {attributeName:'rdtTreatment'},
      {attributeName:'sex'}
    ];
    
    new MDSS.GenericOntologyBrowser("<%=PersonViewDTO.CLASS%>", attributes);
  })
})();
</script>