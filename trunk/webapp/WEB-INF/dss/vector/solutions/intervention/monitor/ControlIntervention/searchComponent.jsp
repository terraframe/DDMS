<%@ include file="/WEB-INF/templates/jsp_includes.jsp"%>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<c:set var="page_title" value="Control_intervention"  scope="request"/>

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
    <mjl:command classes="submitButton" action="dss.vector.solutions.intervention.monitor.ControlInterventionController.forward.mojo" name="create.button" value="Create"/>
  </dl>
</mjl:form>

<h2><fmt:message key="Results"/></h2>

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