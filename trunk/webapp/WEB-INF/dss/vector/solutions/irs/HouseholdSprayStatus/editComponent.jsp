<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.HouseholdSprayStatus.form.name" id="dss.vector.solutions.irs.HouseholdSprayStatus.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.householdIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="householdId" />
        <mjl:messages attribute="householdId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.structureIdMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="structureId" />
        <mjl:messages attribute="structureId">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.bedNetsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="bedNets" />
        <mjl:messages attribute="bedNets">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.householdsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="households" />
        <mjl:messages attribute="households">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.lockedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="locked" />
        <mjl:messages attribute="locked">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.otherMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="other" />
        <mjl:messages attribute="other">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.peopleMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="people" />
        <mjl:messages attribute="people">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.prevSprayedHouseholdsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="prevSprayedHouseholds" />
        <mjl:messages attribute="prevSprayedHouseholds">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.prevSprayedStructuresMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="prevSprayedStructures" />
        <mjl:messages attribute="prevSprayedStructures">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.refusedMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="refused" />
        <mjl:messages attribute="refused">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roomsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="rooms" />
        <mjl:messages attribute="rooms">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.roomsWithBedNetsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="roomsWithBedNets" />
        <mjl:messages attribute="roomsWithBedNets">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_SprayStatus_spray}" param="spray">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="spray">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayedHouseholdsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sprayedHouseholds" />
        <mjl:messages attribute="sprayedHouseholds">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayedRoomsMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sprayedRooms" />
        <mjl:messages attribute="sprayedRooms">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.sprayedStructuresMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="sprayedStructures" />
        <mjl:messages attribute="sprayedStructures">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.structuresMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="structures" />
        <mjl:messages attribute="structures">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mjl:command value="Update" action="dss.vector.solutions.irs.HouseholdSprayStatusController.update.mojo" name="dss.vector.solutions.irs.HouseholdSprayStatus.form.update.button" />
  <mjl:command value="Delete" action="dss.vector.solutions.irs.HouseholdSprayStatusController.delete.mojo" name="dss.vector.solutions.irs.HouseholdSprayStatus.form.delete.button" />
  <mjl:command value="Cancel" action="dss.vector.solutions.irs.HouseholdSprayStatusController.cancel.mojo" name="dss.vector.solutions.irs.HouseholdSprayStatus.form.cancel.button" />
</mjl:form>
