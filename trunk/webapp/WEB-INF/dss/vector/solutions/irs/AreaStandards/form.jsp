<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="areaStandardsId" value="${item.areaStandardsId}"/>
  <mjl:dt attribute="startDate">
    <mjl:input param="startDate" type="text" classes="DatePick" id="startDate"/>
  </mjl:dt>
  <mjl:dt attribute="targetUnit">
    <mjl:select param="targetUnit" items="${targetUnit}" var="current" valueAttribute="enumName">
      <mjl:option selected="${mjl:contains(item.targetUnitEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${item.targetUnitMd.enumItems[current.enumName]}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="unitNozzleAreaCoverage">
    <mjl:input param="unitNozzleAreaCoverage" type="text" />
  </mjl:dt>    
  <mjl:dt attribute="room">
    <mjl:input param="room" type="text" />
  </mjl:dt>
  <mjl:dt attribute="structureArea">
    <mjl:input param="structureArea" type="text" />
  </mjl:dt>
  <mjl:dt attribute="household">
    <mjl:input param="household" type="text" />
  </mjl:dt>
</mjl:component>
