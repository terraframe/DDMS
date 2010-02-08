<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component item="${styles}" param="styles">
  <mjl:dt attribute="fill">
    <mjl:input type="hidden" param="fill" value="${styles.fill}" id="${styles.id}_fill" />
    <div class="colorPickerValue" id="${styles.id}_fill_opener" style="background-color: ${styles.fill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="fontFamily">
    <select name="styles.fontFamily">
      <c:forEach items="${fontFamilies}" var="font">
        <option value="${font}" <c:if test="${styles.fontFamily == font}">selected="selected"</c:if>>${font}</option>
      </c:forEach>
    </select>
     
  </mjl:dt>
  <mjl:dt attribute="fontSize">

    <div id="${styles.id}_fontSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_fontSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_fontSizeDisplay"></span>
      <mjl:input type="hidden" param="fontSize" id="${styles.id}_fontSize" />
    </div>

  </mjl:dt>
  <mjl:dt attribute="fontStyles">
    <mjl:select var="current" valueAttribute="enumName" items="${allFontStyles}" param="fontStyles">
      <mjl:option selected="${mjl:contains(styles.fontStylesEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="labelRotation">

    <div>  
      <canvas id="${styles.id}_labelRotationCanvas" style="border: 1px solid gray"></canvas>
    </div>
    <img id="${styles.id}_labelRotationCCW" class="clickable" style="margin-left:20px" src="imgs/ccwArrow.png" />
    <img id="${styles.id}_labelRotationCW" class="clickable" style="margin-right:10px" src="imgs/cwArrow.png" />
    <span id="${styles.id}_labelRotationDisplay"></span>
    <mjl:input type="hidden" param="labelRotation" id="${styles.id}_labelRotation" />
    
  </mjl:dt>
  <mjl:dt attribute="labelHaloRadius">
  
    <div id="${styles.id}_labelHaloRadiusSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_labelHaloRadiusThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_labelHaloRadiusDisplay"></span>
      <mjl:input type="hidden" param="labelHaloRadius" id="${styles.id}_labelHaloRadius" />
    </div>    
  
  </mjl:dt>
  <mjl:dt attribute="labelHaloFill">
    <mjl:input type="hidden" param="labelHaloFill" value="${styles.labelHaloFill}" id="${styles.id}_labelHaloFill" />
    <div class="colorPickerValue" id="${styles.id}_labelHaloFill_opener" style="background-color: ${styles.labelHaloFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="labelHaloOpacity">
  
    <div id="${styles.id}_labelHaloOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_labelHaloOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_labelHaloOpacityDisplay"></span>
      <mjl:input type="hidden" param="labelHaloOpacity" id="${styles.id}_labelHaloOpacity" />
    </div>  
  
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
  
    <div id="${styles.id}_displacementXSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_displacementXThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_displacementXDisplay"></span>
      <mjl:input type="hidden" param="displacementX" id="${styles.id}_displacementX" />
    </div>
    
  </mjl:dt>
  <mjl:dt attribute="displacementY">

    <div id="${styles.id}_displacementYSliderBG" class="yui-h-slider" style="width: 100px">
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

    <div id="${styles.id}_pointSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointSizeDisplay"></span>
      <mjl:input type="hidden" param="pointSize" id="${styles.id}_pointSize" />
    </div>

  </mjl:dt>
  <mjl:dt attribute="pointRotation">
  
    <div>  
      <canvas id="${styles.id}_pointRotationCanvas" style="border: 1px solid gray"></canvas>
    </div>
    <img id="${styles.id}_pointRotationCCW" class="clickable" style="margin-left:20px" src="imgs/ccwArrow.png" />
    <img id="${styles.id}_pointRotationCW" class="clickable" style="margin-right:10px" src="imgs/cwArrow.png" />
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

    <div id="${styles.id}_pointWidthSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointWidthThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointWidthDisplay"></span>
      <mjl:input type="hidden" param="pointWidth" id="${styles.id}_pointWidth" />
    </div>

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
  <mjl:dt attribute="polygonFill">
    <mjl:input type="hidden" param="polygonFill" value="${styles.polygonFill}" id="${styles.id}_polygonFill" />
    <div class="colorPickerValue" id="${styles.id}_polygonFill_opener" style="background-color: ${styles.polygonFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonFillOpacity">
  
    <div id="${styles.id}_polygonFillOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonFillOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonFillOpacityDisplay"></span>
      <mjl:input type="hidden" param="polygonFillOpacity" id="${styles.id}_polygonFillOpacity" />
    </div>
    
  </mjl:dt>
  <mjl:dt attribute="polygonWidth">

    <div id="${styles.id}_polygonWidthSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonWidthThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonWidthDisplay"></span>
      <mjl:input type="hidden" param="polygonWidth" id="${styles.id}_polygonWidth" />
    </div>

  </mjl:dt>

<script type="text/javascript">
(function(){
  
  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${styles.id}_fill_opener', '${styles.id}_fill');
  picker.attach('${styles.id}_pointStroke_opener', '${styles.id}_pointStroke');
  picker.attach('${styles.id}_polygonFill_opener', '${styles.id}_polygonFill');
  picker.attach('${styles.id}_polygonStroke_opener', '${styles.id}_polygonStroke');
  picker.attach('${styles.id}_labelHaloFill_opener', '${styles.id}_labelHaloFill');

})();
</script>

<script type="text/javascript">
MDSS.MapPanel.attachAnchorSlider('${styles.id}_anchorPointX');
MDSS.MapPanel.attachAnchorSlider('${styles.id}_anchorPointY');

MDSS.MapPanel.attachDisplacementSlider('${styles.id}_displacementX');
MDSS.MapPanel.attachDisplacementSlider('${styles.id}_displacementY');

MDSS.MapPanel.attachRotationCanvas('${styles.id}_labelRotation');
MDSS.MapPanel.attachRotationCanvas('${styles.id}_pointRotation');

MDSS.MapPanel.attachOpacitySlider('${styles.id}_polygonStrokeOpacity');
MDSS.MapPanel.attachOpacitySlider('${styles.id}_polygonFillOpacity');
MDSS.MapPanel.attachOpacitySlider('${styles.id}_pointStrokeOpacity');
MDSS.MapPanel.attachOpacitySlider('${styles.id}_labelHaloOpacity');

MDSS.MapPanel.attach50Slider('${styles.id}_fontSize');
MDSS.MapPanel.attach50Slider('${styles.id}_labelHaloRadius');
MDSS.MapPanel.attach100Slider('${styles.id}_pointWidth');
MDSS.MapPanel.attach100Slider('${styles.id}_pointSize');
MDSS.MapPanel.attach100Slider('${styles.id}_polygonWidth');


// Grab the value of the containing layer's RenderAs value to know
// if we should show or hide the point/polygon styles.
</script>

</mjl:component>