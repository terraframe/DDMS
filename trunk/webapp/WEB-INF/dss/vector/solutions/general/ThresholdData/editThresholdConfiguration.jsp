<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Configure_Thresholds"  scope="request"/>

<jsp:include page="/WEB-INF/selectSearch.jsp"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="PopulationData.search.mojo" method="POST">
  <dl>
    <dt>
      <label><fmt:message key="Epidemic_Universal"/></label>
    </dt>
    <dd>    
      <mjl:select var="current" valueAttribute="geoHierarchyId" param="universal" items="${views}">
        <mjl:option selected="${hierarchy != null && hierarchy == current.geoHierarchyId ? 'selected' : ''}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
    <dt>
      <label> <fmt:message key="Calculation_Method"/></label>
    </dt>
    <dd>
      <mjl:radioGroup var="current" valueAttribute="enumName" items="${methods}" param="calulationMethod">
       <mjl:radioOption  checked="${isEpiWeek != null && isEpiWeek == current.enumName ? 'checked' : ''}">
          ${current.displayLabel}
       </mjl:radioOption>
      </mjl:radioGroup>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.ThresholdDataController.setThresholdConfiguration.mojo" name="search" value="Submit"/>
  </dl>
</mjl:form>