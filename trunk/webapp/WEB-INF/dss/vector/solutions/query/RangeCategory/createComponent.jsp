<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<mjl:component item="${item}" param="dto">
<dl>
  <dt>
    ${item.lowerBoundMd.displayLabel} - ${item.upperBoundMd.displayLabel}
  </dt>
  <dd>
    <div>
    <div style='float: left'>
      <mjl:input type="hidden" param="type" />
      <input maxlength="7" type="hidden" name="dto.thematicColor" value="${item.thematicColor}" id="${item.id}_thematicColor" />
      <mjl:input classes="bounds" type="text" param="lowerBound" />
      &nbsp;-&nbsp;
      <mjl:input type="text" classes="bounds" param="upperBound" />
    </div>

    <div style='float: left'>
      <div class="colorPickerValue" id="${item.id}_opener" style="background-color: ${item.id}">&nbsp;</div>
    </div>
    <div>
  </dd>
</dl>

<script type="text/javascript">
(function(){
  new MDSS.ColorPicker('${item.id}', '${item.id}_opener', '${item.id}_thematicColor');
})()
</script>

</mjl:component>