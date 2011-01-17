<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="View_AreaStandards" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.irs.AreaStandards.form.id" name="dss.vector.solutions.irs.AreaStandards.form.name" method="POST">
    <mjl:input param="id" value="${item.areaStandardsId}" type="hidden" />
    <mjl:component param="dto" item="${item}">
      <mjl:dt attribute="startDate">
        <span class="formatDate">${item.startDate}</span>
      </mjl:dt>
      <mjl:dt attribute="endDate">
        <span class="formatDate">${item.endDate}</span>
      </mjl:dt>
      <mjl:dt attribute="targetUnit">
        <ul>
          <c:forEach items="${item.targetUnitEnumNames}" var="enumName">
            <li>
              ${item.targetUnitMd.enumItems[enumName]}
            </li>
          </c:forEach>
        </ul>
      </mjl:dt>      
      <mjl:dt attribute="unitNozzleAreaCoverage">
        ${item.unitNozzleAreaCoverage}
      </mjl:dt>      
      <mjl:dt attribute="room">
        ${item.room}
      </mjl:dt>
      <mjl:dt attribute="structureArea">
        ${item.structureArea}
      </mjl:dt>
      <mjl:dt attribute="household">
        ${item.household}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.irs.AreaStandards.form.edit.button" value="Edit" action="dss.vector.solutions.irs.AreaStandardsController.edit.mojo" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.irs.AreaStandards.viewAll.link" action="dss.vector.solutions.irs.AreaStandardsController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
