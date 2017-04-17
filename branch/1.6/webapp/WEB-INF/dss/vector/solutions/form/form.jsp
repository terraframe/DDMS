<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>


<%
  
%>

<mjl:component param="form" item="${form}">
    <c:if test="${form.formNameReadable}">
      <li>
        <label>${form.formNameMd.displayLabel}</label>
        <mjl:input value="${form.formName}" type="text" param="formName" />
      </li>
    </c:if>
    <c:if test="${form.displayLabelReadable}">
      <li>
        <label>${form.displayLabelMd.displayLabel}</label>
        <mjl:input value="${form.displayLabel}" type="text" param="displayLabel" />
      </li>
    </c:if>
</mjl:component>