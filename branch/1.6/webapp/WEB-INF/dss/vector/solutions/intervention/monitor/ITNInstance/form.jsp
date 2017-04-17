<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.LocalPropertyDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="dss.vector.solutions.intervention.monitor.ITNInstanceViewDTO"%>
<%@page import="org.json.JSONArray"%>

<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:input type="hidden" param="household" value="${item.household.id}"/>
  <mjl:dt attribute="netId">
    <mjl:input param="netId" type="text" id="netId" /><a href="#" id="getUniqueId"><mdss:localize key="Get_Unique_Id"/></a>
  </mjl:dt>  
  <mjl:dt attribute="netBrand">
    <mdss:mo param="netBrand" value="${netBrand}"/>
  </mjl:dt>  
  <mjl:dt attribute="monthReceived">
    <mjl:select param="monthReceived" items="${monthReceived}" var="current" valueAttribute="enumName" includeBlank="true">
      <mjl:option selected="${mjl:contains(item.monthReceivedEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.monthReceivedMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>  
  <mjl:dt attribute="yearReceived">
    <mjl:input param="yearReceived" type="text" id="yearReceived" />
  </mjl:dt>  
  <mjl:dt attribute="obtained">
    <mdss:mo param="obtained" value="${obtained}"/>
  </mjl:dt>
  <mjl:dt attribute="price">
    <fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" var="formattedPrice" value="${item.price}" />
    <mjl:input param="price" type="text" value="${formattedPrice}" />
  </mjl:dt>
  <mjl:dt attribute="retreated">
    <mjl:boolean param="retreated" id="retreated" />
  </mjl:dt>
  <div class="monthRetreated">
    <mjl:dt attribute="monthRetreated">
      <mjl:select param="monthRetreated" items="${monthRetreated}" var="current" valueAttribute="enumName" includeBlank="true" id="monthRetreated">
        <mjl:option selected="${mjl:contains(item.monthRetreatedEnumNames, current.enumName) ? 'selected' : 'false'}">
          ${item.monthRetreatedMd.enumItems[current.enumName]}
        </mjl:option>
      </mjl:select>
    </mjl:dt>  
  </div>
  <div class="yearRetreated">
    <mjl:dt attribute="yearRetreated">
      <mjl:input param="yearRetreated" type="text" id="yearRetreated"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="damaged">
    <mdss:mo param="damaged" value="${damaged}"/>
  </mjl:dt>  
  <mjl:dt attribute="hanging">
    <mdss:mo param="hanging" value="${hanging}"/>
  </mjl:dt>
  <mjl:dt attribute="notUsedForSleeping">
    <mjl:boolean param="notUsedForSleeping" id="notUsedForSleeping" />
  </mjl:dt>
  <div class="purpose">
    <mjl:dt attribute="purpose">
      <mdss:mo param="purpose" value="${purpose}"/>
    </mjl:dt>
  </div>
  <div class="purposeComments">
    <mjl:dt attribute="purposeComments">
      <mjl:textarea param="purposeComments" rows="3" id="purposeComments" />
    </mjl:dt>
  </div>
  <mjl:dt attribute="washed">
    <mjl:select param="washed" items="${washed}" var="current" valueAttribute="enumName" id="washed">
      <mjl:option selected="${mjl:contains(item.washedEnumNames, current.enumName) ? 'selected' : 'false'}" id="washed.${current.enumName}">
        ${item.washedMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <div class="washFrequency">
    <mjl:dt attribute="washFrequency">
      <mjl:input param="washFrequency" type="text" id="washFrequency"/>
    </mjl:dt>
  </div>
  <div class="washPeriod">
    <mjl:dt attribute="washPeriod">
      <mdss:mo param="washPeriod" value="${washPeriod}"/>
    </mjl:dt>
  </div>
  <mjl:dt attribute="sleptUnderNet">
    <c:if test="${!item.isNetUsed}">
      <mjl:input param="sleptUnderNet" type="text" />
    </c:if>
    <c:if test="${item.isNetUsed}">
      ${item.sleptUnderNet}
      <mjl:input param="sleptUnderNet" type="hidden" value="${item.sleptUnderNet}" />
    </c:if>
  </mjl:dt>  
</mjl:component>
<%=Halp.loadTypes(Arrays.asList(new String[]{LocalPropertyDTO.CLASS}))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP YEAR COMBO BOXES
    //**********************************************************
    MDSS.GenericSearch.createYearSearch('yearReceived');
    MDSS.GenericSearch.createYearSearch('yearRetreated');
        
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var triggers = new Array('washed', 'washed.NO', 'washed.DONT_KNOW');
    
    MDSS.ElementHandler.setupBooleanHandler('retreated.positive', 'retreated.negative', MDSS.HiddenInputElement.toArray(['monthRetreated', 'yearRetreated']));
    MDSS.ElementHandler.setupBooleanHandler('notUsedForSleeping.positive', 'notUsedForSleeping.negative', MDSS.HiddenInputElement.toArray(['purpose', 'purposeComments']));
    MDSS.ElementHandler.setupSelectHandler('washed.YES', triggers, MDSS.HiddenInputElement.toArray(['washFrequency', 'washPeriod']));    
    
    //**********************************************************
    // SETUP UNIQUE ID GENERATOR
    //**********************************************************    
    YAHOO.util.Event.on('getUniqueId', 'click', function(e, obj){
      var request = new MDSS.Request({
          onSend: function(){},
          onComplete: function(){},
          onSuccess : function(result){
          document.getElementById('netId').value = result;
        }
      });
      Mojo.$.dss.vector.solutions.LocalProperty.getNextId(request);
    });
  })
})();
</script>
