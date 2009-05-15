<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>

<mjl:form name="dss.vector.solutions.intervention.monitor.Household.form.name" id="dss.vector.solutions.intervention.monitor.Household.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <mjl:component item="${item}" param="dto">
    <mjl:dt attribute="householdName">
      ${item.householdName}
    </mjl:dt>
    
    <mjl:dt attribute="urban">
      ${item.urban?item.urbanMd.positiveDisplayLabel:item.urbanMd.negativeDisplayLabel}
    </mjl:dt>

    <mjl:dt attribute="people">
      ${item.people}
    </mjl:dt>

    <mjl:dt attribute="wall">
      ${item.wall.displayLabel}    
    </mjl:dt>

    <mjl:dt attribute="wallInfo">
      ${item.wallInfo}
    </mjl:dt>

    <mjl:dt attribute="roof">
      ${item.roof.displayLabel}
    </mjl:dt>
    
    <mjl:dt attribute="roofInfo">
      ${item.roofInfo}
    </mjl:dt>

    <mjl:dt attribute="hasWindows">
      ${item.hasWindows?item.hasWindowsMd.positiveDisplayLabel:item.hasWindowsMd.negativeDisplayLabel}
    </mjl:dt>
    
    <mjl:dt attribute="windowType">
      <ul>
        <c:forEach var="enumName" items="${item.windowTypeEnumNames}">
          <li>
            ${item.windowTypeMd.enumItems[enumName]}
          </li>
        </c:forEach>
      </ul>
    </mjl:dt>

    <mjl:dt attribute="rooms">
      ${item.rooms}
     </mjl:dt>
    
    <mjl:dt attribute="lastSprayed">
      ${item.lastSprayed}
    </mjl:dt>

    <mjl:dt attribute="nets">
      ${item.nets}
      </mjl:dt>
    <mjl:dt attribute="netsUsed">
      ${item.netsUsed}
    </mjl:dt>
    <mjl:dt attribute="sleptUnderNets">
      ${item.sleptUnderNets}
    </mjl:dt>
    <dt>
    </dt>
    <dd>
      <table class="displayTable">
        <tr>
          <th><fmt:message key="Nets" /></th>
          <th><fmt:message key="Amount" /></th>
        </tr>
        <mjl:components items="${nets}" param="nets" var="current" varStatus="status">
          <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
            <c:choose>
              <c:when test="${current.child.isAbstract}">
                <td colspan="2">
                  ${current.child.displayLabel}
                </td>
              </c:when>
              <c:otherwise>
                <td style="padding-left:2em">
                  ${current.child.displayLabel}
                </td>
                <td style="text-align:right;">
                  ${current.amount}
                </td>
              </c:otherwise>
            </c:choose>
          </tr>
        </mjl:components>
      </table>
    </dd>
    </mjl:component>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.HouseholdController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Household.form.edit.button" />
  </dl>
</mjl:form>
<dl>
  <dt><fmt:message key="People" /> </dt>
  <dd>
    <table class="displayTable">
      <c:forEach items="${people}" var="current" varStatus="status">
       <tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}">
         <td>
           ${current.personId}
         </td>
         <td>
           <mjl:commandLink display="View" action="dss.vector.solutions.intervention.monitor.PersonController.view.mojo" name="Person.view.link">
             <mjl:property name="id" value="${current.id}"/>
           </mjl:commandLink>
         </td>
        </tr>
      </c:forEach>
    </table>
  </dd>
  <mjl:commandLink display="Add a person" action="dss.vector.solutions.intervention.monitor.PersonController.newInstance.mojo" name="Person.newInstance.link">
    <mjl:property name="householdId" value="${item.id}"/>
  </mjl:commandLink>
</dl>
<mjl:commandLink display="Back_To_Survey_Point" action="dss.vector.solutions.intervention.monitor.SurveyPointController.view.mojo" name="Household.view.link">
  <mjl:property name="id" value="${item.surveyPoint.id}" />
</mjl:commandLink>
