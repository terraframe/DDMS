<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_LocalPersistenceStrategy" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategy.form.name" id="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategy.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategy.form.edit.button" action="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategyController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategy.viewAll.link" action="dss.vector.solutions.kaleidoscope.data.etl.LocalPersistenceStrategyController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
