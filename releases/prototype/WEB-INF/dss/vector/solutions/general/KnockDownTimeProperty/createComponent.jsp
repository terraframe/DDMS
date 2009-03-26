<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.general.KnockDownTimeProperty.form.name" id="dss.vector.solutions.general.KnockDownTimeProperty.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.insecticideMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${insecticide}" param="insecticide">
          <mjl:option>
            ${current.displayLabel}
          </mjl:option>
        </mjl:select>
      </dd>
      <dt>
        <label>
          ${item.lowerPercentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lowerPercent" />
        <mjl:messages attribute="lowerPercent">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lowerTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="lowerTime" />
        <mjl:messages attribute="lowerTime">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.upperPercentMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="upperPercent" />
        <mjl:messages attribute="upperPercent">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.upperTimeMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="upperTime" />
        <mjl:messages attribute="upperTime">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Create" action="dss.vector.solutions.general.KnockDownTimePropertyController.create.mojo" name="dss.vector.solutions.general.KnockDownTimeProperty.form.create.button" />
</mjl:form>
