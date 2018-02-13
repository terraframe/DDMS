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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<%@page import="dss.vector.solutions.util.Halp"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.intervention.monitor.HouseholdDTO"%>
<%@page import="dss.vector.solutions.intervention.monitor.HouseholdViewDTO"%>    
<%@page import="dss.vector.solutions.LocalPropertyDTO"%>

<mjl:component item="${item}" param="dto">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:input type="hidden" param="surveyPoint" value="${item.surveyPoint.id}" />

      <mjl:dt attribute="householdName">
        <mjl:input type="text" param="householdName" id="householdId"/><a href="#" id="getUniqueId"><mdss:localize key="Get_Unique_Id"/></a>
      </mjl:dt>

      <mjl:dt attribute="urban">
        <mjl:boolean param="urban" />
      </mjl:dt>

      <mjl:dt attribute="people">
        <mjl:input type="text" param="people" />
      </mjl:dt>

      <mjl:dt attribute="wall">
        <mdss:mo param="wall" value="${wall}"/>
      </mjl:dt>

      <mjl:dt attribute="wallInfo">
        <mjl:input type="text" param="wallInfo" />
      </mjl:dt>

      <mjl:dt attribute="roof">
        <mdss:mo param="roof" value="${roof}"/>
      </mjl:dt>

      <mjl:dt attribute="roofInfo">
        <mjl:input type="text" param="roofInfo" />
      </mjl:dt>

      <mjl:dt attribute="hasWindows">
        <mjl:boolean param="hasWindows" id="hasWindows"/>
      </mjl:dt>
      
      <div class="windowType">
        <mjl:dt attribute="windowType">
          <mdss:mo param="windowType" value="${windowType}"/>
        </mjl:dt>
      </div>

      <mjl:dt attribute="rooms">
        <mjl:input type="text" param="rooms" />
      </mjl:dt>
      
      <mjl:dt attribute="hasBeenSprayed">
        <mjl:group type="radio" param="hasBeenSprayed" items="${hasBeenSprayed}" var="current" valueAttribute="enumName">
          <mjl:groupOption checked="${mjl:contains(item.hasBeenSprayedEnumNames, current.enumName) ? 'checked' : 'false'}" id="hasBeenSprayed.${current.enumName}">
            ${item.hasBeenSprayedMd.enumItems[current.enumName]}
          </mjl:groupOption>
        </mjl:group>    
      </mjl:dt>     
      
      <div class="lastSprayed">
        <mjl:dt attribute="lastSprayed">
          <mjl:input type="text" param="lastSprayed" id="lastSprayed" size="2" maxlength="2"/>
        </mjl:dt>
      </div>

      <mjl:dt attribute="nets">
        <c:if test="${!item.hasHouseholdNets}">
          <mjl:input type="text" param="nets" />
        </c:if>
        <c:if test="${item.hasHouseholdNets}">
          ${item.nets}
          <mjl:input type="hidden" param="nets" value="${item.nets}" />
        </c:if>      
      </mjl:dt>
      
    </mjl:component>
  
<%String[] types_to_load = {LocalPropertyDTO.CLASS};%>
<%=Halp.loadTypes((List<String>) Arrays.asList(types_to_load))%>

<script type="text/javascript">
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    //**********************************************************
    // SETUP FIELD HIDING
    //**********************************************************    
    var triggers = new Array('hasBeenSprayed.NO', 'hasBeenSprayed.DONT_KNOW');
    
    MDSS.ElementHandler.setupBooleanHandler('hasWindows.positive', 'hasWindows.negative', MDSS.HiddenInputElement.toArray(['windowType']));
    MDSS.ElementHandler.setupBooleanHandler('hasBeenSprayed.YES', triggers, MDSS.HiddenInputElement.toArray(['lastSprayed']));    
  
    //**********************************************************
    // SETUP UNIQUE ID GENERATOR
    //**********************************************************      
    YAHOO.util.Event.on('getUniqueId', 'click', function(e, obj){
      var request = new MDSS.Request({
        onSend: function(){},
        onComplete: function(){},
        onSuccess : function(result){
          document.getElementById('householdId').value = result;
        }
      });
      Mojo.$.dss.vector.solutions.LocalProperty.getNextId(request);
    });
  })
})();
</script>