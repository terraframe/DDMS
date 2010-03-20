<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Edit_Lethal_Property"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.LethalTimeProperty.form.name" id="dss.vector.solutions.general.LethalTimeProperty.form.id" method="POST">
  <dl>
    <%@ include file="form.jsp"%>
    
    <mjl:command value="Update" action="dss.vector.solutions.general.LethalTimePropertyController.update.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.update.button" />
    <mjl:command value="Delete" action="dss.vector.solutions.general.LethalTimePropertyController.delete.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.delete.button" />
    <mjl:command value="Cancel" action="dss.vector.solutions.general.LethalTimePropertyController.cancel.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.cancel.button" />
  </dl>
</mjl:form>
