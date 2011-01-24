<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Define_Insecticide"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.Insecticide.form.name" id="dss.vector.solutions.general.Insecticide.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
  
    <mdss:localize key="Define_Insecticide" var="Localized_Define_Insecticide" />
  
    <mjl:command value="${Localized_Define_Insecticide}" action="dss.vector.solutions.general.InsecticideController.create.mojo" name="dss.vector.solutions.general.Insecticide.form.create.button" />
  </dl>
</mjl:form>
