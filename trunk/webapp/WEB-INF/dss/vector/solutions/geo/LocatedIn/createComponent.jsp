<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.geo.LocatedIn.form.name" id="dss.vector.solutions.geo.LocatedIn.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.geo.LocatedInController.create.mojo" name="dss.vector.solutions.geo.LocatedIn.form.create.button" />
</mjl:form>
