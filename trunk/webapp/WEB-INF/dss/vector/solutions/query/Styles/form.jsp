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
  <mjl:dt attribute="labelRotation">

    <div>  
    <canvas id="${styles.id}_labelRotationCanvas" style="border: 1px solid black;" width="16" height="16"></canvas>
    </div>
    
    <input type="button" value="CCW" id="${styles.id}_labelRotationCCW" />
    <input type="button" value="CW" id="${styles.id}_labelRotationCW" />
    <span id="${styles.id}_labelRotationDisplay"></span>
    <mjl:input type="hidden" param="labelRotation" id="${styles.id}_labelRotation" />
    
  </mjl:dt>
  <mjl:dt attribute="anchorPointX">

    <div id="${styles.id}_anchorPointXSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_anchorPointXThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_anchorPointXDisplay"></span>
      <mjl:input type="hidden" param="anchorPointX" id="${styles.id}_anchorPointX" />
    </div>

  </mjl:dt>
  <mjl:dt attribute="anchorPointY">

    <div id="${styles.id}_anchorPointYSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_anchorPointYThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_anchorPointYDisplay"></span>
      <mjl:input type="hidden" param="anchorPointY" id="${styles.id}_anchorPointY" />
    </div>
    
  </mjl:dt>
  <mjl:dt attribute="displacementX">
  
    <div id="${styles.id}_displacementXSliderBG" class="yui-h-slider" style="width: 200px">
      <div id="${styles.id}_displacementXThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_displacementXDisplay"></span>
      <mjl:input type="hidden" param="displacementX" id="${styles.id}_displacementX" />
    </div>
    
  </mjl:dt>
  <mjl:dt attribute="displacementY">

    <div id="${styles.id}_displacementYSliderBG" class="yui-h-slider" style="width: 200px">
      <div id="${styles.id}_displacementYThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_displacementYDisplay"></span>
      <mjl:input type="hidden" param="displacementY" id="${styles.id}_displacementY" />
    </div>

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
  <mjl:dt attribute="pointSize">
    <mjl:input type="text" param="pointSize"/>
  </mjl:dt>
  <mjl:dt attribute="pointRotation">
  
    <canvas id="${styles.id}_pointRotationCanvas" style="border: 1px solid black;" width="16" height="16"></canvas>
    <span id="${styles.id}_pointRotationDisplay"></span>
    <mjl:input type="hidden" param="pointRotation" id="${styles.id}_pointRotation" />
    
  </mjl:dt>
  <mjl:dt attribute="pointStrokeOpacity">
  
    <div id="${styles.id}_pointStrokeOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointStrokeOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointStrokeOpacityDisplay"></span>
      <mjl:input type="hidden" param="pointStrokeOpacity" id="${styles.id}_pointStrokeOpacity" />
    </div>
    
  </mjl:dt>
  <mjl:dt attribute="pointWidth">
    <mjl:input type="text" param="pointWidth" />
  </mjl:dt>
  <mjl:dt attribute="polygonFill">
    <mjl:input type="hidden" param="polygonFill" value="${styles.polygonFill}" id="${styles.id}_polygonFill" />
    <div class="colorPickerValue" id="${styles.id}_polygonFill_opener" style="background-color: ${styles.polygonFill}">&nbsp;</div>
  <mjl:dt attribute="polygonFillOpacity">
  
    <div id="${styles.id}_polygonFillOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonFillOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonFillOpacityDisplay"></span>
      <mjl:input type="hidden" param="polygonFillOpacity" id="${styles.id}_polygonFillOpacity" />
    </div>
    
  </mjl:dt>
  </mjl:dt>
  <mjl:dt attribute="polygonStroke">
    <mjl:input type="hidden" param="polygonStroke" value="${styles.polygonStroke}" id="${styles.id}_polygonStroke" />
    <div class="colorPickerValue" id="${styles.id}_polygonStroke_opener" style="background-color: ${styles.polygonStroke}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonStrokeOpacity">

    <div id="${styles.id}_polygonStrokeOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonStrokeOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonStrokeOpacityDisplay"></span>
      <mjl:input type="hidden" param="polygonStrokeOpacity" id="${styles.id}_polygonStrokeOpacity" />
    </div>

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

<script type="text/javascript">
MDSS.MapPanel.attachAnchorSlider('${styles.id}_anchorPointX');
MDSS.MapPanel.attachAnchorSlider('${styles.id}_anchorPointY');

MDSS.MapPanel.attachDisplacementSlider('${styles.id}_displacementX');
MDSS.MapPanel.attachDisplacementSlider('${styles.id}_displacementY');

/*
MDSS.MapPanel.attachRotationCanvas('${styles.id}_labelRotation');
MDSS.MapPanel.attachRotationCanvas('${styles.id}_pointRotation');
*/

MDSS.MapPanel.attachOpacitySlider('${styles.id}_polygonStrokeOpacity');
MDSS.MapPanel.attachOpacitySlider('${styles.id}_polygonFillOpacity');
MDSS.MapPanel.attachOpacitySlider('${styles.id}_pointStrokeOpacity');

</script>

</mjl:component>