<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.LethalTimeProperty.form.name" id="dss.vector.solutions.general.LethalTimeProperty.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.insecticideMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.insecticide.displayLabel}" action="dss.vector.solutions.general.InsecticideController.view.mojo" name="dss.vector.solutions.general.Insecticide.form.view.link">
        <mjl:property value="${item.insecticide.id}" name="id" />
      </mjl:commandLink>
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
  <mjl:command value="Edit" action="dss.vector.solutions.general.LethalTimePropertyController.edit.mojo" name="dss.vector.solutions.general.LethalTimeProperty.form.edit.button" />
  <br />
</mjl:form>
<dl>
</dl>
<mjl:commandLink display="View All" action="dss.vector.solutions.general.LethalTimePropertyController.viewAll.mojo" name="dss.vector.solutions.general.LethalTimeProperty.viewAll.link" />
