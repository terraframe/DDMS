<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="mdss" uri="/WEB-INF/tlds/mdssLib.tld" %>
<c:set var="page_title" scope="request" value="View_MappableAttribute" />
<mjl:messages>
  <mjl:message />
</mjl:messages>
<dl>
  <mjl:form method="POST" name="dss.vector.solutions.kaleidoscope.MappableAttribute.form.name" id="dss.vector.solutions.kaleidoscope.MappableAttribute.form.id">
    <mjl:input param="id" type="hidden" value="${item.id}" />
    <mjl:component item="${item}" param="dto">
      <mjl:dt attribute="aggregatable">
        ${item.aggregatable ? item.aggregatableMd.positiveDisplayLabel : item.aggregatableMd.negativeDisplayLabel}
      </mjl:dt>
      <mjl:dt attribute="wrappedMdAttribute">
        ${item.wrappedMdAttribute.keyName}
      </mjl:dt>
    </mjl:component>
    <mjl:command name="dss.vector.solutions.kaleidoscope.MappableAttribute.form.edit.button" action="dss.vector.solutions.kaleidoscope.MappableAttributeController.edit.mojo" value="Edit" />
  </mjl:form>
</dl>
<mjl:commandLink name="dss.vector.solutions.kaleidoscope.MappableAttribute.viewAll.link" action="dss.vector.solutions.kaleidoscope.MappableAttributeController.viewAll.mojo">
  <mdss:localize key="View_All" />
</mjl:commandLink>
