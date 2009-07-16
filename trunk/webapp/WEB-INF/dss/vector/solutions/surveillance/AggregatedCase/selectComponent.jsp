<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="Select_age_group"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>

<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<dl>
  <dt> <label> <fmt:message key="Change_age_group"/> </label> </dt>
  <dd>
    <mjl:form name="search.form.name" id="search.form" method="POST">
      <mjl:input type="hidden" param="geoId" value="${geoId}" />
      <mjl:input type="hidden" param="period" value="${period}" />
      <mjl:input type="hidden" param="periodType" value="${periodType}"/>
      <mjl:input type="hidden" param="year" value="${year}"/>
      ${item.ageGroupMd.displayLabel}
      <mjl:select var="current" valueAttribute="id" includeBlank="true" items="${ageGroups}"  param="ageGroup.componentId" id="search.select.id">
        <mjl:option>
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
        }
</script>