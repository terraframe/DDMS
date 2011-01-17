<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_ITNInstance" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.intervention.monitor.ITNInstance.form.id" name="dss.vector.solutions.intervention.monitor.ITNInstance.form.name" method="POST">
    <mjl:input param="id" value="${item.concreteId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="netId">
        ${item.netId}
      </mjl:dt>      
      <mjl:dt attribute="netBrand">
        ${item.netBrand.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="monthReceived">
        <ul>
          <c:forEach items="${item.monthReceivedEnumNames}" var="enumName">
            <li>
              ${item.monthReceivedMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>      
      <mjl:dt attribute="yearReceived">
        ${item.yearReceived}
      </mjl:dt>      
      <mjl:dt attribute="obtained">
        ${item.obtained.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="price">
        ${item.price}
      </mjl:dt>
      <mjl:dt attribute="retreated">
        ${item.retreated ? item.retreatedMd.positiveDisplayLabel : item.retreatedMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="monthRetreated">
        <ul>
          <c:forEach items="${item.monthRetreatedEnumNames}" var="enumName">
            <li>
              ${item.monthRetreatedMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>      
      <mjl:dt attribute="yearRetreated">
        ${item.yearRetreated}
      </mjl:dt>      
      <mjl:dt attribute="damaged">
        ${item.damaged.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="hanging">
        ${item.hanging.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="notUsedForSleeping">
        ${item.notUsedForSleeping ? item.notUsedForSleepingMd.positiveDisplayLabel : item.notUsedForSleepingMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="purpose">
        ${item.purpose.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="purposeComments">
        ${item.purposeComments}
      </mjl:dt>
      <mjl:dt attribute="washed">
        <ul>
          <c:forEach items="${item.washedEnumNames}" var="enumName">
            <li>
              ${item.washedMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>      
      <mjl:dt attribute="washFrequency">
        ${item.washFrequency}
      </mjl:dt>
      <mjl:dt attribute="washPeriod">
        ${item.washPeriod.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="sleptUnderNet">
        ${item.sleptUnderNet}
      </mjl:dt>      
    </mjl:component>
    <mjl:command name="dss.vector.solutions.intervention.monitor.ITNInstance.form.edit.button" value="Edit" action="dss.vector.solutions.intervention.monitor.ITNInstanceController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink action="dss.vector.solutions.intervention.monitor.HouseholdController.view.mojo" name="Household.view.link">
  <mdss:localize key="Back_To_Household"/>
  <mjl:property name="id" value="${item.household.id}" />
</mjl:commandLink>
