<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Add_New_Locale" />
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="LocalizationController.name" id="LocalizationController.form.id" method="POST">
  <dl>
    <dt>
      <fmt:message key="language"/>
    </dt>
    <dd>
      <select name="language">
        <c:forEach
          items="${languages}"
          var="l">
          <option value="${l.language}">${l.displayLanguage}</option>
        </c:forEach>
      </select>
    </dd>
    <dt>
      <fmt:message key="country_optional"/>
    </dt>
    <dd>
      <select name="country">
        <option value="" />
        <c:forEach
          items="${countries}"
          var="country">
          <option value="${country.country}">${country.displayCountry}</option>
        </c:forEach>
      </select>
    </dd>
    <dt>
      <fmt:message key="variant_optional"/>
    </dt>
    <dd>
      <mjl:input type="text" param="variant"/>
    </dd>
    <mjl:command value="installLocale" action="dss.vector.solutions.util.LocalizationController.installLocale.mojo" name="LocalizationController.form.installLocale.button" />
  </dl>
</mjl:form>