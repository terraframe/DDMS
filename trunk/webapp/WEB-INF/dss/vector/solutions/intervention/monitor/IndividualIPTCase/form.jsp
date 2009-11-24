<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<input type="hidden" name="serviceDate" value="${serviceDate}"/>
<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="patient">
    ${item.patient.firstName} ${item.patient.lastName}
    <mjl:input type="hidden" param="patient" value="${item.patient.id}"/>
  </mjl:dt>
  <mjl:dt attribute="residentialLocation">
    <mjl:input classes="geoInput" id="geoIdEl" param="residentialLocation" type="text" />
  </mjl:dt>
  
</mjl:component>
