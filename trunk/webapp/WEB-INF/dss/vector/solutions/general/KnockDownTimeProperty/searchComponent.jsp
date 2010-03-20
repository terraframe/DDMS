<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Search_Knock_Down_Properties"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="search" method="POST" id ="searchForm">
    <dt>
      <label>
        <fmt:message key="Insecticide_Active_Ingredient"/>
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticideId">
        <mjl:option selected="${(insecticideId != null && current.id == insecticideId) ? true : false}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
    <mjl:command classes="submitButton" action="dss.vector.solutions.general.KnockDownTimePropertyController.searchByInsecticide.mojo" name="search.button" value="Search" />
  </mjl:form>
</dl>
