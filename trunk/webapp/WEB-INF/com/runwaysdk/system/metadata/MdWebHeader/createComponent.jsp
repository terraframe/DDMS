<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set scope="request" var="page_title" value="Create_MdWebHeader" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<h2 class="fieldTitle">${item.md.displayLabel}</h2>
  <mjl:form id="com.runwaysdk.system.metadata.MdWebHeader.form.id" name="com.runwaysdk.system.metadata.MdWebHeader.form.name" method="POST">
<dl>
    <%@include file="form.jsp" %>
</dl>
    <%@include file="../MdWebAttribute/createActions.jsp" %>
  </mjl:form>
