<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="page_title" value="Found_Knock_Down_Property"  scope="request"/>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.KnockDownTimeProperty.form.name" id="dss.vector.solutions.general.KnockDownTimeProperty.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
${item.insecticide.displayLabel}
    </dd>
    <dt>
      <label>
        ${item.lowerPercentMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lowerPercent}
    </dd>
    <dt>
      <label>
        ${item.lowerTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.lowerTime}
    </dd>
    <dt>
      <label>
        ${item.upperPercentMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.upperPercent}
    </dd>
    <dt>
      <label>
        ${item.upperTimeMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.upperTime}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.general.KnockDownTimePropertyController.edit.mojo" name="dss.vector.solutions.general.KnockDownTimeProperty.form.edit.button" />
  <br />
</mjl:form>
<mjl:commandLink display="View All" action="dss.vector.solutions.general.KnockDownTimePropertyController.viewAll.mojo" name="dss.vector.solutions.general.KnockDownTimeProperty.viewAll.link" />
