<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<mjl:component item="${item}" param="dto">
<dl id="${item.id}_dl">
  <dt>
    ${item.exactValueMd.displayLabel}
  </dt>
  <dd>
    <div style='float: left'>
      <mjl:input type="hidden" param="type" />
      <input  maxlength="7" type="hidden" name="dto.thematicColor" value="${item.thematicColor}" id="${item.id}_thematicColor" />
      <mjl:input classes="bounds" type="text" param="exactValue" />
    </div>
    <div style='float: left'>
      <div class="colorPickerValue" id="${item.id}_opener" style="background-color: ${item.thematicColor}">&nbsp;</div>
    </div>
    <div style='float: left; margin-top: 3px; margin-left: 15px'>
      <img id="${item.id}_delBtn" src="imgs/icons/delete.png" class="clickable"/>
    </div>
  </dd>
</dl>

<script type="text/javascript">
(function(){
	
  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${item.id}_opener', '${item.id}_thematicColor', '${item.thematicColor}');

// deletes the category from the DOM
YAHOO.util.Event.on("${item.id}_delBtn", 'click', function(e, dlId){
  var el = document.getElementById(dlId);
  el.parentNode.removeChild(el);
}, "${item.id}_dl", this);

})();
</script>

</mjl:component>
