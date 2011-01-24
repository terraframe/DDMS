<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Search_Lethal_Properties"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form name="search" method="POST" id ="searchForm">
    <dt>
      <label>
        <mdss:localize key="Insecticide_Active_Ingredient"/>
      </label>
    </dt>
    <dd>
      <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticideId">
        <mjl:option selected="${(insecticideId != null && current.id == insecticideId) ? true : false}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </dd>
  <mdss:localize key="Search" var="Localized_Search" />
  <mjl:command classes="submitButton" action="dss.vector.solutions.general.LethalTimePropertyController.searchByInsecticide.mojo" name="search.button" value="${Localized_Search}" />
  </mjl:form>
</dl>