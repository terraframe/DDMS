<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%@page import="dss.vector.solutions.PersonViewDTO"%>
<%@page import="dss.vector.solutions.PersonController"%>
<%@page import="dss.vector.solutions.PersonDTO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="dss.vector.solutions.util.Halp"%>

<c:set var="page_title" value="Search_ITNDistribution" scope="request" />

<mjl:messages>
  <mjl:message />
</mjl:messages>

<jsp:include page="/WEB-INF/selectSearch.jsp" />

<dl>
  <mjl:form name="dss.vector.solutions.intervention.monitor.ITNDistribution.form.name" id="dss.vector.solutions.intervention.monitor.ITNDistribution.form.id" method="POST">
    <mjl:component item="${item}" param="view">
      <mjl:dt attribute="facility">
        <mdss:geo param="facility" concrete="false" value="${item.facility}" filter="${healthFacility}" />
      </mjl:dt>
      <mjl:dt attribute="distributionDate" >
        <mjl:input type="text" param="distributionDate" id="distributionDate" classes="DatePick NoFuture"/>
      </mjl:dt>
      <mjl:dt attribute="person">
      <table>
        <tr>
          <td> <label class="sublabel"> ${person.identifierMd.displayLabel}  </label></td>        
          <td> <label class="sublabel"> ${person.firstNameMd.displayLabel}  </label></td>
          <td> <label class="sublabel"> ${person.lastNameMd.displayLabel}  </label></td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>
            <mjl:input type="text" param="identifier.search" id="identifier.search" value="${person != null ? person.identifier : ''}" />
          </td>        
          <td>
            <mjl:input type="text" id="firstName.search" value="${person != null ? person.firstName : ''}" />
          </td>
          <td>
            <mjl:input type="text" id="lastName.search" value="${person != null ? person.lastName : ''}" />
          </td>
          <td>
            <mjl:input type="hidden" param="person" id="person"/>

            <span class="clickable" id="person.span">
              <span id="createPerson">
                <mdss:localize key="Create_new_recipient"/>        
              </span>
              <span id="editPerson" >
                <mdss:localize key="Edit_recipient"/>        
              </span>
            </span>
          </td>
        </tr>
    </table>
    </mjl:dt>
  </mjl:component>

  <mdss:localize key="Search" var="Localized_Search" />

  <mjl:command value="${Localized_Search}" action="dss.vector.solutions.intervention.monitor.ITNDistributionController.viewHistory.mojo" name="search.person" id="button.id" />
  </mjl:form>
</dl>

<jsp:include page="/WEB-INF/excelButtons.jsp">
  <jsp:param value="dss.vector.solutions.export.ITNDistributionExcelView" name="excelType"/>
</jsp:include>

<%=Halp.loadTypes(Arrays.asList(new String[]{PersonDTO.CLASS, PersonController.CLASS, PersonViewDTO.CLASS}))%>

<script type="text/javascript" >
(function(){
  YAHOO.util.Event.onDOMReady(function(){
    var prop = {
      elements : ['identifier.search', 'firstName.search', 'lastName.search'],
      concrete : 'person',
      createLink : 'createPerson',
      editLink : 'editPerson', 
      clickable : 'person.span', 
      calendar : 'dateOfBirth', 
      firstName: 'firstName', 
      lastName: 'lastName',
      identifier: 'identifier',
      button : 'button.id'
    };

  
    new MDSS.PersonModal(prop);
  });
})();
</script>
