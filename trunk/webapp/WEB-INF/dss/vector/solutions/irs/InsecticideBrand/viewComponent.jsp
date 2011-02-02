<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_InsecticideBrand" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.irs.InsecticideBrand.form.id" name="dss.vector.solutions.irs.InsecticideBrand.form.name" method="POST">
    <mjl:input param="id" value="${item.id}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="productName">
        ${item.productName.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="activeIngredient">
        ${item.activeIngredient.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="concentrationQuantifier">
        ${item.concentrationQuantifier}
      </mjl:dt>
      <mjl:dt attribute="concentrationQualifier">
        <ul>
          <c:forEach items="${item.concentrationQualifierEnumNames}" var="enumName">
            <li>
              ${item.concentrationQualifierMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="insecticideUse">
        <ul>
          <c:forEach items="${item.insecticideUseEnumNames}" var="enumName">
            <li>
              ${item.insecticideUseMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="useDetail">
        ${item.useDetail.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="unitsPerApplication">
        ${item.unitsPerApplication}
      </mjl:dt>
      <mjl:dt attribute="unitQuantifier">
        ${item.unitQuantifier}
      </mjl:dt>
      <mjl:dt attribute="unitQualifier">
        <ul>
          <c:forEach items="${item.unitQualifierEnumNames}" var="enumName">
            <li>
              ${item.unitQualifierMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="enabled">
        ${item.enabled ? item.enabledMd.positiveDisplayLabel : item.enabledMd.negativeDisplayLabel}
      </mjl:dt>
    </mjl:component>
    <mdss:localize key="Edit" var="Localized_Edit" />
    <mjl:command name="dss.vector.solutions.irs.InsecticideBrand.form.edit.button" value="${Localized_Edit}" action="dss.vector.solutions.irs.InsecticideBrandController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.irs.InsecticideBrand.viewAll.link" action="dss.vector.solutions.irs.InsecticideBrandController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
