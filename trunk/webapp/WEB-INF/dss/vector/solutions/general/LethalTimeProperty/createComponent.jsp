<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Create_Lethal_Property"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.LethalTimeProperty.form.name" id="dss.vector.solutions.general.LethalTimeProperty.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Create" var="Localized_Create" />
  
    <mjl:command value="${Localized_Create}" action="dss.vector.solutions.general.LethalTimePropertyController.create.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.create.button" />
  </dl>
</mjl:form>
