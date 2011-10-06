<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<mjl:component param="mdField" item="${item}">
  <%@include file="../MdWebField/form.jsp" %>
  <mjl:dt attribute="beforeTodayExclusive">
    <mjl:boolean param="beforeTodayExclusive" />
  </mjl:dt>
  <mjl:dt attribute="beforeTodayInclusive">
    <mjl:boolean param="beforeTodayInclusive" />
  </mjl:dt>
  <mjl:dt attribute="afterTodayExclusive">
    <mjl:boolean param="afterTodayExclusive" />
  </mjl:dt>
  <mjl:dt attribute="afterTodayInclusive">
    <mjl:boolean param="afterTodayInclusive" />
  </mjl:dt>
  <mjl:dt attribute="startDate">
    <mjl:input id="${item.id}_startDate" param="startDate" type="text" />
  </mjl:dt>
  <mjl:dt attribute="endDate">
    <mjl:input id="${item.id}_endDate" param="endDate" type="text" />
  </mjl:dt>
</mjl:component>
<script type="text/javascript">
(function(){
  var sd = document.getElementById('${item.id}_startDate');
  var ed = document.getElementById('${item.id}_endDate');

  MDSS.Calendar.addCalendarListeners(sd);
  MDSS.Calendar.addCalendarListeners(ed);
  
  sd.value = MDSS.Calendar.getLocalizedString(sd.value);
  ed.value = MDSS.Calendar.getLocalizedString(ed.value);
})();
</script>
