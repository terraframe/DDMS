<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="page_title" value="Edit_Control_intervention"  scope="request"/>

<mjl:form name="ControlIntervention.search" method="POST" id="ControlIntervention">
  <dl>
    <mjl:component item="${item}" param="view">
      <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
      <mjl:dt attribute="geoEntity">
        <mdss:geo param="geoEntity" political="false" populated="false" spray="false" urban="true" value="${item.geoEntity}" />
      </mjl:dt>
      <mjl:dt attribute="startDate">
        <mjl:input param="startDate" type="text" classes="DatePick NoFuture" id="startDate"/>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <mjl:input param="endDate" type="text" classes="DatePick NoFuture" id="endDate"/>
      </mjl:dt>
    </mjl:component>
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.searchByView.mojo" name="search.button" value="Search"/>
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.forward.mojo" name="create.button" value="Go_Create"/>
  </dl>
</mjl:form>

<h2><fmt:message key="Results"/></h2>
<c:choose> 
  <c:when test="${query.count > 0}" > 
	<mjl:table var="current" query="${query}" classes="displayTable" even="evenRow" odd="oddRow">
	  <mjl:context action="dss.vector.solutions.intervention.monitor.ControlInterventionController.searchByParameters.mojo">  
	    <mjl:property name="geoId" value="${item.geoEntity.id}"/>
	    <mjl:property name="startDate" value="${startDate}"/>
	    <mjl:property name="endDate" value="${endDate}"/>
	  </mjl:context>
	  <mjl:columns>
	    <mjl:attributeColumn attributeName="geoEntity">
	      <mjl:row>
	        ${current.geoEntity.displayString}
	      </mjl:row>
	    </mjl:attributeColumn>    
	    <mjl:attributeColumn attributeName="startDate">
	      <mjl:row>
	        <fmt:formatDate value="${current.startDate}" pattern="${dateFormatPattern}"  />
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:attributeColumn attributeName="endDate">
	      <mjl:row>
	        <fmt:formatDate value="${current.endDate}" pattern="${dateFormatPattern}"  />
	      </mjl:row>
	    </mjl:attributeColumn>
	    <mjl:freeColumn>
	      <mjl:header>
	
	      </mjl:header>
	      <mjl:row>
	        <mjl:commandLink action="dss.vector.solutions.intervention.monitor.ControlInterventionController.view.mojo" name="view.link">
	          <fmt:message key="View" />
	          <mjl:property value="${current.concreteId}" name="id" />
	        </mjl:commandLink>
	      </mjl:row>
	      <mjl:footer>
	      </mjl:footer>
	    </mjl:freeColumn>
	  </mjl:columns>
	  <mjl:pagination>
	    <mjl:page />
	  </mjl:pagination>  
	</mjl:table>
  </c:when> 
  <c:otherwise>
  	<span class="nomatch"><fmt:message key="Search_Found_No_Results"/></span>
  </c:otherwise> 
</c:choose>  
<br />

<form id="export" name="export" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.exportExcelTemplate.mojo" method="post" target="messageFrame">
  <fmt:message key="Excel_Export_Header" var="export_label"/>
  <input type="submit" class="submitButton" name="export.button" value="${export_label}"/>
</form>
<form id="import" name="import" action="excelimport" method="post">
  <fmt:message key="Excel_Import_Header" var="import_label"/>
  <input type="submit" class="submitButton" name="import.button" value="${import_label}"/>
</form>

<script type="text/javascript">
(function(){
	YAHOO.util.Event.onDOMReady(function(){
			  		
    // attach load listener to Iframe to receive message when error occurs during
    // export operations
    YAHOO.util.Event.on('messageFrame', 'load', function(e){
      var body = e.target.contentDocument.getElementsByTagName('body')[0];
      var text = typeof body.textContent !== 'undefined' ? body.textContent : body.innerText;
      text = MDSS.util.stripWhitespace(text);
      if(text.length > 0)
      {
        new MDSS.ErrorModal(text);
      }

    }, null, this);
  })
})();  
</script>

<iframe id="messageFrame" name="messageFrame" style="display: none; width: 1px; height: 1px;"></iframe>