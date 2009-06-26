<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Search_Knock_Down_Properties"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="search" method="POST" id ="searchForm">
  <dl>
    <dt>
        <label>
        <fmt:message key="Insecticide_Active_Ingredient"/>
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
  <mjl:command classes="submitButton" action="dss.vector.solutions.general.KnockDownTimePropertyController.searchByInsecticide.mojo" name="search.button" value="Search" />
</mjl:form>
