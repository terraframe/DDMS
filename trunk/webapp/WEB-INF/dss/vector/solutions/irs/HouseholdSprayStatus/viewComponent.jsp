<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.HouseholdSprayStatus.form.name" id="dss.vector.solutions.irs.HouseholdSprayStatus.form.id" method="POST">
  <mjl:input value="${item.id}" type="hidden" param="id" />
  <dl>
    <dt>
      <label>
        ${item.householdIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.householdId}
    </dd>
    <dt>
      <label>
        ${item.structureIdMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.structureId}
    </dd>
    <dt>
      <label>
        ${item.bedNetsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.bedNets}
    </dd>
    <dt>
      <label>
        ${item.householdsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.households}
    </dd>
    <dt>
      <label>
        ${item.lockedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.locked}
    </dd>
    <dt>
      <label>
        ${item.otherMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.other}
    </dd>
    <dt>
      <label>
        ${item.peopleMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.people}
    </dd>
    <dt>
      <label>
        ${item.prevSprayedHouseholdsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.prevSprayedHouseholds}
    </dd>
    <dt>
      <label>
        ${item.prevSprayedStructuresMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.prevSprayedStructures}
    </dd>
    <dt>
      <label>
        ${item.refusedMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.refused}
    </dd>
    <dt>
      <label>
        ${item.roomsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.rooms}
    </dd>
    <dt>
      <label>
        ${item.roomsWithBedNetsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.roomsWithBedNets}
    </dd>
    <dt>
      <label>
        ${item.sprayMd.displayLabel}
      </label>
    </dt>
    <dd>
      <mjl:commandLink display="${item.spray.keyName}" action="dss.vector.solutions.irs.AbstractSprayController.view.mojo" name="dss.vector.solutions.irs.AbstractSpray.form.view.link">
        <mjl:property value="${item.spray.id}" name="id" />
      </mjl:commandLink>
    </dd>
    <dt>
      <label>
        ${item.sprayedHouseholdsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sprayedHouseholds}
    </dd>
    <dt>
      <label>
        ${item.sprayedRoomsMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sprayedRooms}
    </dd>
    <dt>
      <label>
        ${item.sprayedStructuresMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.sprayedStructures}
    </dd>
    <dt>
      <label>
        ${item.structuresMd.displayLabel}
      </label>
    </dt>
    <dd>
      ${item.structures}
    </dd>
  </dl>
  <mjl:command value="Edit" action="dss.vector.solutions.irs.HouseholdSprayStatusController.edit.mojo" name="dss.vector.solutions.irs.HouseholdSprayStatus.form.edit.button" />
  <br />
</mjl:form>

<mjl:commandLink display="View All" action="dss.vector.solutions.irs.HouseholdSprayStatusController.viewAll.mojo" name="dss.vector.solutions.irs.HouseholdSprayStatus.viewAll.link" />
