<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<mjl:component item="${styles}" param="styles">
<mjl:input type="hidden" value="${styles.id}" id="categoryStyleId"></mjl:input>
<strong><fmt:message key="Advanced_Label_Options" /></strong>: <span id="${styles.id}_toggleLabel" class="clickable"><fmt:message key="Toggle_Show" /></span>
<div id="${styles.id}_toggleLabelDiv"  style="display: block; margin-bottom: 10px;">
<dl>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_fill" ${styles.enable_fill ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.fillMd.displayLabel }</label>
  </dt>
  <dd>
    <mjl:input type="hidden" param="fill" value="${styles.fill}" id="${styles.id}_fill" />
    <div class="colorPickerValue" id="${styles.id}_fill_opener" style="background-color: ${styles.fill}">&nbsp;</div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_fontFamily" ${styles.enable_fontFamily ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.fontFamilyMd.displayLabel }</label>
  </dt>
  <dd>
    <select name="styles.fontFamily">
      <c:forEach items="${fontFamilies}" var="font">
        <option value="${font}" <c:if test="${styles.fontFamily == font}">selected="selected"</c:if>>${font}</option>
      </c:forEach>
    </select>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_fontSize" ${styles.enable_fontSize ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.fontSizeMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_fontSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_fontSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_fontSizeDisplay"></span>
      <mjl:input type="hidden" param="fontSize" id="${styles.id}_fontSize" />
    </div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_fontStyles" ${styles.enable_fontStyles ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.fontStylesMd.displayLabel }</label>
  </dt>
  <dd>
    <mjl:select var="current" valueAttribute="enumName" items="${allFontStyles}" param="fontStyles">
      <mjl:option selected="${mjl:contains(styles.fontStylesEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_labelRotation" ${styles.enable_labelRotation ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.labelRotationMd.displayLabel }</label>
  </dt>
  <dd>
    <div>  
      <canvas id="${styles.id}_labelRotationCanvas" style="border: 1px solid gray"></canvas>
    </div>
    <img id="${styles.id}_labelRotationCCW" class="clickable" style="margin-left:20px" src="imgs/ccwArrow.png" />
    <img id="${styles.id}_labelRotationCW" class="clickable" style="margin-right:10px" src="imgs/cwArrow.png" />
    <span id="${styles.id}_labelRotationDisplay"></span>
    <mjl:input type="hidden" param="labelRotation" id="${styles.id}_labelRotation" />
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_labelHaloRadius" ${styles.enable_labelHaloRadius ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.labelHaloRadiusMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_labelHaloRadiusSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_labelHaloRadiusThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_labelHaloRadiusDisplay"></span>
      <mjl:input type="hidden" param="labelHaloRadius" id="${styles.id}_labelHaloRadius" />
    </div>    
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_labelHaloFill" ${styles.enable_labelHaloFill ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.labelHaloFillMd.displayLabel }</label>
  </dt>
  <dd>
    <mjl:input type="hidden" param="labelHaloFill" value="${styles.labelHaloFill}" id="${styles.id}_labelHaloFill" />
    <div class="colorPickerValue" id="${styles.id}_labelHaloFill_opener" style="background-color: ${styles.labelHaloFill}">&nbsp;</div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_labelHaloOpacity" ${styles.enable_labelHaloOpacity ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.labelHaloOpacityMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_labelHaloOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_labelHaloOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_labelHaloOpacityDisplay"></span>
      <mjl:input type="hidden" param="labelHaloOpacity" id="${styles.id}_labelHaloOpacity" />
    </div>  
  </dd>  
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_anchorPointX" ${styles.enable_anchorPointX ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.anchorPointXMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_anchorPointXSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_anchorPointXThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_anchorPointXDisplay"></span>
      <mjl:input type="hidden" param="anchorPointX" id="${styles.id}_anchorPointX" />
    </div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_anchorPointY" ${styles.enable_anchorPointY ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.anchorPointYMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_anchorPointYSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_anchorPointYThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_anchorPointYDisplay"></span>
      <mjl:input type="hidden" param="anchorPointY" id="${styles.id}_anchorPointY" />
    </div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_displacementX" ${styles.enable_displacementX ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.displacementXMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_displacementXSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_displacementXThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_displacementXDisplay"></span>
      <mjl:input type="hidden" param="displacementX" id="${styles.id}_displacementX" />
    </div>
  </dd>
  <dt>
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_displacementY" ${styles.enable_displacementY ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.displacementYMd.displayLabel }</label>
  </dt>
  <dd>
    <div id="${styles.id}_displacementYSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_displacementYThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_displacementYDisplay"></span>
      <mjl:input type="hidden" param="displacementY" id="${styles.id}_displacementY" />
    </div>
  </dd>
</dl>
</div>
<hr />
<strong><fmt:message key="Advanced_Geometry_Options" /></strong>: <span id="${styles.id}_toggleGeo" class="clickable"><fmt:message key="Toggle_Show" /></span>
<div id="${styles.id}_toggleGeoDiv"  style="display: block; margin-bottom: 10px;">
<div id="pointStyles">
<dl>
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointMarker" ${styles.enable_pointMarker ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointMarkerMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <mjl:select var="current" valueAttribute="enumName" items="${pointMarker}" param="pointMarker">
      <mjl:option selected="${mjl:contains(styles.pointMarkerEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </dd>
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointStroke" ${styles.enable_pointStroke ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointStrokeMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <mjl:input type="hidden" param="pointStroke" value="${styles.pointStroke}" id="${styles.id}_pointStroke" />
    <div class="colorPickerValue" id="${styles.id}_pointStroke_opener" style="background-color: ${styles.pointStroke}">&nbsp;</div>
  </dd>
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointSize" ${styles.enable_pointSize ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointSizeMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <div id="${styles.id}_pointSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointSizeDisplay"></span>
      <mjl:input type="hidden" param="pointSize" id="${styles.id}_pointSize" />
    </div>
  </dd>
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointRotation" ${styles.enable_pointRotation ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointRotationMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <div>  
      <canvas id="${styles.id}_pointRotationCanvas" style="border: 1px solid gray"></canvas>
    </div>
    <img id="${styles.id}_pointRotationCCW" class="clickable" style="margin-left:20px" src="imgs/ccwArrow.png" />
    <img id="${styles.id}_pointRotationCW" class="clickable" style="margin-right:10px" src="imgs/cwArrow.png" />
    <span id="${styles.id}_pointRotationDisplay"></span>
    <mjl:input type="hidden" param="pointRotation" id="${styles.id}_pointRotation" />
  </dd>
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointStrokeOpacity" ${styles.enable_pointStrokeOpacity ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointStrokeOpacityMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <div id="${styles.id}_pointStrokeOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointStrokeOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointStrokeOpacityDisplay"></span>
      <mjl:input type="hidden" param="pointStrokeOpacity" id="${styles.id}_pointStrokeOpacity" />
    </div>
  </dd>  
  <dt class="POINT_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_pointWidth" ${styles.enable_pointWidth ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.pointWidthMd.displayLabel }</label>
  </dt>
  <dd class="POINT_toggle">
    <div id="${styles.id}_pointWidthSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_pointWidthThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_pointWidthDisplay"></span>
      <mjl:input type="hidden" param="pointWidth" id="${styles.id}_pointWidth" />
    </div>
  </dd>
</dl>
</div>
<div id="polygonStyles">
<dl>
  <dt class="POLYGON_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_polygonStroke" ${styles.enable_polygonStroke ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.polygonStrokeMd.displayLabel }</label>
  </dt>
  <dd class="POLYGON_toggle">
    <mjl:input type="hidden" param="polygonStroke" value="${styles.polygonStroke}" id="${styles.id}_polygonStroke" />
    <div class="colorPickerValue" id="${styles.id}_polygonStroke_opener" style="background-color: ${styles.polygonStroke}">&nbsp;</div>
  </dd>
  <dt class="POLYGON_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_polygonStrokeOpacity" ${styles.enable_polygonStrokeOpacity ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.polygonStrokeOpacityMd.displayLabel }</label>
  </dt>
  <dd class="POLYGON_toggle">
    <div id="${styles.id}_polygonStrokeOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonStrokeOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonStrokeOpacityDisplay"></span>
      <mjl:input type="hidden" param="polygonStrokeOpacity" id="${styles.id}_polygonStrokeOpacity" />
    </div>
  </dd>
  <dt class="POLYGON_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_polygonFill" ${styles.enable_polygonFill ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.polygonFillMd.displayLabel }</label>
  </dt>
  <dd class="POLYGON_toggle">
    <mjl:input type="hidden" param="polygonFill" value="${styles.polygonFill}" id="${styles.id}_polygonFill" />
    <div class="colorPickerValue" id="${styles.id}_polygonFill_opener" style="background-color: ${styles.polygonFill}">&nbsp;</div>
  </dd>
  <dt class="POLYGON_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_polygonFillOpacity" ${styles.enable_polygonFillOpacity ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.polygonFillOpacityMd.displayLabel }</label>
  </dt>
  <dd class="POLYGON_toggle">
    <div id="${styles.id}_polygonFillOpacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonFillOpacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonFillOpacityDisplay"></span>
      <mjl:input type="hidden" param="polygonFillOpacity" id="${styles.id}_polygonFillOpacity" />
    </div>
  </dd>  
  <dt class="POLYGON_toggle">
    <c:if test="${enableCheckboxes}"><input type="checkbox" name="styles.enable_polygonWidth" ${styles.enable_polygonWidth ? 'checked="checked"' : ''} value="true" title='<fmt:message key="Enable_Style_Override" />' /></c:if>
    <label>${styles.polygonWidthMd.displayLabel }</label>
  </dt>
  <dd class="POLYGON_toggle">
    <div id="${styles.id}_polygonWidthSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${styles.id}_polygonWidthThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${styles.id}_polygonWidthDisplay"></span>
      <mjl:input type="hidden" param="polygonWidth" id="${styles.id}_polygonWidth" />
    </div>
  </dd>
</dl>
</div>
</div>
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
</script>

</mjl:component>