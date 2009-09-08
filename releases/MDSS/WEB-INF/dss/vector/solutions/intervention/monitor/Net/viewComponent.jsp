<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="page_title" value="View_Net"  scope="request"/>

<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.intervention.monitor.Net.form.name" id="dss.vector.solutions.intervention.monitor.Net.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.displayLabelMd.displayLabel}
      </label>
    </dt>
    <dd>
      <dl>
        <dt>
          <label>
            ${item.displayLabel.defaultLocaleMd.displayLabel}
          </label>
        </dt>
        <dd>
          ${item.displayLabel.defaultLocale}
        </dd>
      </dl>
    </dd>
    <dt>
      <label>
        ${item.enabledMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.enabled}
    </dd>
    <dt>
      <label>
        ${item.isAbstractMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.isAbstract}
    </dd>
    <dt>
      <label>
        ${item.netNameMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.netName}
    </dd>
    <dt>
      <label>
        ${item.parentNetMd.displayLabel}
      </label>
    </dt>
    <dd>
       ${item.parentNet.displayLabel}
    </dd>
    <mjl:command value="Edit" action="dss.vector.solutions.intervention.monitor.NetController.edit.mojo" name="dss.vector.solutions.intervention.monitor.Net.form.edit.button" />
  </dl>
</mjl:form>
<mjl:commandLink name="View_All" action="dss.vector.solutions.intervention.monitor.NetController.viewAll.mojo"><fmt:message key="View_All"/></mjl:commandLink>