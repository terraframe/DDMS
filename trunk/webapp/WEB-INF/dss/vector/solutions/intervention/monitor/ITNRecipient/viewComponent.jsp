<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.ITNRecipient.form.name" id="dss.vector.solutions.intervention.monitor.ITNRecipient.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.personMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.person.keyName}" action="dss.vector.solutions.PersonController.view.mojo" name="dss.vector.solutions.Person.form.view.link">
        <mjl:property value="${item.person.id}" name="id" />
      </mjl:commandLink>
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.ITNRecipientController.edit.mojo" name="dss.vector.solutions.intervention.monitor.ITNRecipient.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.intervention.monitor.ITNRecipientController.viewAll.mojo" name="dss.vector.solutions.intervention.monitor.ITNRecipient.viewAll.link" />
