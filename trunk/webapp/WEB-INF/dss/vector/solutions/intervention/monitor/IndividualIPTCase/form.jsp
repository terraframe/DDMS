<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/selectSearch.jsp" />

<input type="hidden" name="serviceDate" value="${serviceDate}"/>
<%@include file="personHeader.jsp" %>
<mjl:component param="dto" item="${item}">
  <mjl:input type="hidden" param="patient" value="${person.personId}"/>
  <mjl:input type="hidden" param="concreteId" value="${item.concreteId}"/>
  <mjl:dt attribute="residentialLocation">
    <mdss:geo param="residentialLocation" concrete="false" />
  </mjl:dt>
  
</mjl:component>
