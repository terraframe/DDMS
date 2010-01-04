<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component item="${styles}" param="styles">
  <mjl:dt attribute="fill">
    <mjl:input type="hidden" param="fill" value="${styles.fill}" id="${styles.id}_fill" />
    <div class="colorPickerValue" id="${styles.id}_fill_opener" style="background-color: ${styles.fill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <mjl:input type="text" param="fontFamily" />
  </mjl:dt>
  <mjl:dt attribute="fontSize">
    <mjl:input type="text" param="fontSize" />
  </mjl:dt>
  <mjl:dt attribute="fontStyle">
    <mjl:input type="text" param="fontStyle" />
  </mjl:dt>
  <mjl:dt attribute="pointMarker">
    <mjl:select var="current" valueAttribute="enumName" items="${pointMarker}" param="pointMarker">
      <mjl:option selected="${mjl:contains(styles.pointMarkerEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="pointStroke">
    <mjl:input type="hidden" param="pointStroke" value="${styles.pointStroke}" id="${styles.id}_pointStroke" />
    <div class="colorPickerValue" id="${styles.id}_pointStroke_opener" style="background-color: ${styles.pointStroke}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="pointWidth">
    <mjl:input type="text" param="pointWidth" />
  </mjl:dt>
  <mjl:dt attribute="polygonFill">
    <mjl:input type="hidden" param="polygonFill" value="${styles.polygonFill}" id="${styles.id}_polygonFill" />
    <div class="colorPickerValue" id="${styles.id}_polygonFill_opener" style="background-color: ${styles.polygonFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonStroke">
    <mjl:input type="hidden" param="polygonStroke" value="${styles.polygonStroke}" id="${styles.id}_polygonStroke" />
    <div class="colorPickerValue" id="${styles.id}_polygonStroke_opener" style="background-color: ${styles.polygonStroke}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonWidth">
    <mjl:input type="text" param="polygonWidth" />
  </mjl:dt>

<script type="text/javascript">
(function(){
  
  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${styles.id}_fill_opener', '${styles.id}_fill');
  picker.attach('${styles.id}_pointStroke_opener', '${styles.id}_pointStroke');
  picker.attach('${styles.id}_polygonFill_opener', '${styles.id}_polygonFill');
  picker.attach('${styles.id}_polygonStroke_opener', '${styles.id}_polygonStroke');

})();
</script>
</mjl:component>
