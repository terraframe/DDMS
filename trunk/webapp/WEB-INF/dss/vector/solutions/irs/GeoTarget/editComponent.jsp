<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%><%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<mjl:messages>
  <mjl:message />
</mjl:messages>
<mjl:form name="dss.vector.solutions.irs.GeoTarget.form.name" id="dss.vector.solutions.irs.GeoTarget.form.id" method="POST">
  <mjl:component item="${item}" param="dto">
    <dl>
      <dt>
        <label>
          ${item.geoEntityMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:select var="current" valueAttribute="id" items="${dss_vector_solutions_irs_GeoTarget_geoEntity}" param="geoEntity">
          <mjl:option>
            ${current.keyName}
          </mjl:option>
        </mjl:select>
        <mjl:messages attribute="geoEntity">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.targetYearMd.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="targetYear" />
        <mjl:messages attribute="targetYear">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_0Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_0" />
        <mjl:messages attribute="target_0">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_1Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_1" />
        <mjl:messages attribute="target_1">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_10Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_10" />
        <mjl:messages attribute="target_10">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_11Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_11" />
        <mjl:messages attribute="target_11">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_12Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_12" />
        <mjl:messages attribute="target_12">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_13Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_13" />
        <mjl:messages attribute="target_13">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_14Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_14" />
        <mjl:messages attribute="target_14">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_15Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_15" />
        <mjl:messages attribute="target_15">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_16Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_16" />
        <mjl:messages attribute="target_16">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_17Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_17" />
        <mjl:messages attribute="target_17">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_18Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_18" />
        <mjl:messages attribute="target_18">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_19Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_19" />
        <mjl:messages attribute="target_19">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_2Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_2" />
        <mjl:messages attribute="target_2">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_20Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_20" />
        <mjl:messages attribute="target_20">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_21Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_21" />
        <mjl:messages attribute="target_21">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_22Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_22" />
        <mjl:messages attribute="target_22">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_23Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_23" />
        <mjl:messages attribute="target_23">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_24Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_24" />
        <mjl:messages attribute="target_24">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_25Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_25" />
        <mjl:messages attribute="target_25">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_26Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_26" />
        <mjl:messages attribute="target_26">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_27Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_27" />
        <mjl:messages attribute="target_27">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_28Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_28" />
        <mjl:messages attribute="target_28">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_29Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_29" />
        <mjl:messages attribute="target_29">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_3Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_3" />
        <mjl:messages attribute="target_3">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_30Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_30" />
        <mjl:messages attribute="target_30">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_31Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_31" />
        <mjl:messages attribute="target_31">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_32Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_32" />
        <mjl:messages attribute="target_32">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_33Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_33" />
        <mjl:messages attribute="target_33">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_34Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_34" />
        <mjl:messages attribute="target_34">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_35Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_35" />
        <mjl:messages attribute="target_35">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_36Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_36" />
        <mjl:messages attribute="target_36">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_37Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_37" />
        <mjl:messages attribute="target_37">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_38Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_38" />
        <mjl:messages attribute="target_38">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_39Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_39" />
        <mjl:messages attribute="target_39">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_4Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_4" />
        <mjl:messages attribute="target_4">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_40Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_40" />
        <mjl:messages attribute="target_40">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_41Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_41" />
        <mjl:messages attribute="target_41">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_42Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_42" />
        <mjl:messages attribute="target_42">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_43Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_43" />
        <mjl:messages attribute="target_43">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_44Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_44" />
        <mjl:messages attribute="target_44">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_45Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_45" />
        <mjl:messages attribute="target_45">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_46Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_46" />
        <mjl:messages attribute="target_46">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_47Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_47" />
        <mjl:messages attribute="target_47">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_48Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_48" />
        <mjl:messages attribute="target_48">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_49Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_49" />
        <mjl:messages attribute="target_49">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_5Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_5" />
        <mjl:messages attribute="target_5">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_50Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_50" />
        <mjl:messages attribute="target_50">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_51Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_51" />
        <mjl:messages attribute="target_51">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_52Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_52" />
        <mjl:messages attribute="target_52">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_6Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_6" />
        <mjl:messages attribute="target_6">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_7Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_7" />
        <mjl:messages attribute="target_7">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_8Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_8" />
        <mjl:messages attribute="target_8">
          <mjl:message />
        </mjl:messages>
      </dd>
      <dt>
        <label>
          ${item.target_9Md.displayLabel}
        </label>
      </dt>
      <dd>
        <mjl:input type="text" param="target_9" />
        <mjl:messages attribute="target_9">
          <mjl:message />
        </mjl:messages>
      </dd>
    </dl>
  </mjl:component>
  <mdss:localize key="Update" var="Localized_Update" />
  <mjl:command value="${Localized_Update}" action="dss.vector.solutions.irs.GeoTargetController.update.mojo" name="dss.vector.solutions.irs.GeoTarget.form.update.button" />
  <mdss:localize key="Delete" var="Localized_Delete" />
  <mjl:command value="${Localized_Delete}" action="dss.vector.solutions.irs.GeoTargetController.delete.mojo" name="dss.vector.solutions.irs.GeoTarget.form.delete.button" />
  <mdss:localize key="Cancel" var="Localized_Cancel" />
  <mjl:command value="${Localized_Cancel}" action="dss.vector.solutions.irs.GeoTargetController.cancel.mojo" name="dss.vector.solutions.irs.GeoTarget.form.cancel.button" />
</mjl:form>
