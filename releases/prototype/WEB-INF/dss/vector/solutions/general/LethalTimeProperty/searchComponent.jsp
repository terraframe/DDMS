<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="search" method="POST" id ="searchForm">
  <dl>
    <dt>
        <label>
          Insecticide Active Ingredient
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticideId">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
  </dl>  
  <mjl:command classes="submitButton" action="dss.vector.solutions.general.LethalTimePropertyController.searchByInsecticide.mojo" name="search.button" value="Search" />
</mjl:form>
