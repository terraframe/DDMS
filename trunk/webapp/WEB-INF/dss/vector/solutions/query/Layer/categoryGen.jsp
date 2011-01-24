<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="request" var="page_title" value="Create_CategoryGen" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form id="dss.vector.solutions.query.LayerController.form.id" name="dss.vector.solutions.query.LayerController.form.name" method="POST">
    <%@include file="../CategoryGen/form.jsp" %>
    <mdss:localize key="Generate" var="Localized_Generate" />
    <mjl:command name="dss.vector.solutions.query.LayerController.generateCategories.button" value="${Localized_Generate}" action="dss.vector.solutions.query.LayerController.generateCategories.mojo" />
  </mjl:form>
</dl>