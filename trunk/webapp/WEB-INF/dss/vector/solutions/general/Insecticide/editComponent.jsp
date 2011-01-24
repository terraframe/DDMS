<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
<c:set var="page_title" value="Edit_Insecticide"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.Insecticide.form.name" id="dss.vector.solutions.general.Insecticide.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.activeIngredientMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_general_Insecticide_activeIngredient}" param="activeIngredient">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.amountMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="amount" />
        <mjl:messages attribute="amount">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.unitsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="enumName" items="${dss_vector_solutions_general_Insecticide_units}" param="units">
          <c:choose>
            <c:when test="${mjl:contains(item.unitsEnumNames, current.enumName)}">
              <mjl:option selected="selected">
                ${item.unitsMd.enumItems[current.enumName]}
              </mjl:option>
            </c:when>
            <c:otherwise>
              <mjl:option>
                ${item.unitsMd.enumItems[current.enumName]}
              </mjl:option>
            </c:otherwise>
          </c:choose>
        </mjl:select>
      </dd>
    </dl>
  </mjl:component>
  <mdss:localize key="Update" var="Localized_Update" />
  <mjl:command value="${Localized_Update}" action="dss.vector.solutions.general.InsecticideController.update.mojo" name="dss.vector.solutions.general.Insecticide.form.update.button" />
  <mdss:localize key="Delete" var="Localized_Delete" />
  <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.general.InsecticideController.delete.mojo" name="dss.vector.solutions.general.Insecticide.form.delete.button" />
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.general.InsecticideController.cancel.mojo" name="dss.vector.solutions.general.Insecticide.form.cancel.button" />
</mjl:form>
--%>