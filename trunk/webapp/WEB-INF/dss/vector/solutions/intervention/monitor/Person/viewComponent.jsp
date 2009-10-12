<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Person"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Person.form.name" id="dss.vector.solutions.intervention.monitor.Person.form.id" method="POST">
  <dl>
    <mjl:input value="${item.concreteId}" type="hidden" param="id" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="personId">
        ${item.personId}
      </mjl:dt>
      <mjl:dt attribute="dob">
        <span id="dob" class="formatDate"> ${item.dob} </span>
      </mjl:dt>
      <mjl:dt attribute="age">
        ${item.age}
      </mjl:dt>
      <mjl:dt attribute="sex">
        <c:if test="${sex != null}">
          ${sex.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="pregnant">
        ${item.pregnant}
      </mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        ${item.sleptUnderNet}
      </mjl:dt>
      <mjl:dt attribute="haemoglobinMeasured">
        ${item.haemoglobinMeasured}
      </mjl:dt>
      <mjl:dt attribute="haemoglobin">
        ${item.haemoglobin}
      </mjl:dt>
      <mjl:dt attribute="anaemiaTreatment">
        <c:if test="${anaemiaTreatment != null}">
          ${anaemiaTreatment.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="iron">
        ${item.iron}
      </mjl:dt>
      <mjl:dt attribute="performedRDT">
        <c:if test="${performedRDT != null}">
          ${performedRDT.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="bloodslide">
        <c:if test="${bloodslide != null}">
          ${bloodslide.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="rDTResult">
        <ul>
          <c:forEach var="enumName" items="${item.RDTResultEnumNames}">
            <li>${item.RDTResultMd.enumItems[enumName]}</li>
          </c:forEach>
        </ul>
      </mjl:dt>
      <mjl:dt attribute="rdtTreatment">
        <c:if test="${rdtTreatment != null}">
          ${rdtTreatment.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="fever">
        <c:if test="${fever != null}">
          ${fever.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="feverTreatment">
        <c:if test="${feverTreatment != null}">
          ${feverTreatment.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="malaria">
        <c:if test="${malaria != null}">
          ${malaria.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="malariaTreatment">
        <c:if test="${malariaTreatment != null}">
          ${malariaTreatment.displayLabel}
        </c:if>
      </mjl:dt>
      <mjl:dt attribute="payment">
        <c:if test="${payment != null}">
          ${payment.displayLabel}
        </c:if>
      </mjl:dt>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.PersonController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Person.form.edit.button" />
  </dl>
</mjl:form>

<mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
  <fmt:message key="Back_To_Household"/>
  <mjl:property name="id" value="${item.household.id}" />
</mjl:commandLink>

<div id="cal1Container" class="yui-skin-sam"></div>
