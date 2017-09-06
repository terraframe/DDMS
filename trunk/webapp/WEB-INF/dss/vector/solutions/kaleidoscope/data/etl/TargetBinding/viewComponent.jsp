<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_TargetBinding" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.data.etl.TargetBinding.form.name" id="dss.vector.solutions.kaleidoscope.data.etl.TargetBinding.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="sourceView">
        ${item.sourceView.displayLabel}
      </mjl:dt>
      <mjl:dt attribute="targetBusiness">
        ${item.targetBusiness.displayLabel}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.data.etl.TargetBinding.form.edit.button" action="dss.vector.solutions.kaleidoscope.data.etl.TargetBindingController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.data.etl.TargetBinding.viewAll.link" action="dss.vector.solutions.kaleidoscope.data.etl.TargetBindingController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
