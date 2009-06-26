<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<dl>
  <dt> <label> <fmt:message key="Change_age_group"/> </label> </dt>
  <dd>
    <mjl:form name="search.form.name" id="search.form" method="POST">
      <mjl:input type="hidden" param="geoId" value="${item.geoEntity.geoId}" />
      <mjl:input type="hidden" param="period" value="${item.period}" />
      <mjl:input type="hidden" param="periodType" value="${item.periodTypeEnumNames[0]}"/>
      <mjl:input type="hidden" param="year" value="${item.periodYear}"/>
      ${item.ageGroupMd.displayLabel}
      <mjl:select var="current" valueAttribute="id" includeBlank="true" items="${ageGroups}"  param="ageGroup.componentId" id="search.select.id">
        <mjl:option selected="${current.id == item.ageGroup.id ? 'selected' : 'false'}">
          ${current.displayLabel}
        </mjl:option>
      </mjl:select>
    </mjl:form>
  </dd>
</dl>

<script type='text/javascript'>
        jumpBoxForm = document.getElementById('search.form');
        selectBox = document.getElementById('search.select.id'); 
        selectBox.onchange = function(){
          if(selectBox.value != "") {
            jumpBoxForm.action = 'dss.vector.solutions.surveillance.AggregatedCaseController.searchByGeoIdAndEpiWeek.mojo';
            jumpBoxForm.submit();
          }
          else {
            jumpBoxForm.action = 'dss.vector.solutions.surveillance.AggregatedCaseController.selectAgeGroup.mojo';
            jumpBoxForm.submit();              
          }
        }
</script>
