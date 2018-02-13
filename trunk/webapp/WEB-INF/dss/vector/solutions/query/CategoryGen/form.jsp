<%--

    Copyright (C) 2008 IVCC

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@page import="java.util.List"%>
<%@page import="com.runwaysdk.business.ValueObjectDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>

<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:component param="categoryGen" item="${item}">
  <mjl:input param="layerId" type="hidden" />
  <mjl:dt attribute="factoryType">
    <select id="factories" name="categoryGen.factoryType">
      <c:forEach items="${factories}" var="factory">
        <option value="${factory.type}">${factory.display}</option>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt id="categoryCountDT" attribute="categoryCount" classes="factoryAttribute">
    <mjl:input param="categoryCount" type="text" />
  </mjl:dt>
  <mjl:dt id="precisionFiguresDT" attribute="precisionFigures" classes="factoryAttribute">
    <mjl:input param="precisionFigures" type="text" />
  </mjl:dt>
  <mjl:dt attribute="fontFillStart">
    <mjl:input type="hidden" param="fontFillStart" value="${item.fontFillStart}" id="${item.id}_fontFillStart" />
    <div class="colorPickerValue" id="${item.id}_fontFillStart_opener" style="background-color: ${item.fontFillStart}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="fontFillEnd">
    <mjl:input type="hidden" param="fontFillEnd" value="${item.fontFillEnd}" id="${item.id}_fontFillEnd" />
    <div class="colorPickerValue" id="${item.id}_fontFillEnd_opener" style="background-color: ${item.fontFillEnd}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="labelHaloFillStart">
    <mjl:input type="hidden" param="labelHaloFillStart" value="${item.labelHaloFillStart}" id="${item.id}_labelHaloFillStart" />
    <div class="colorPickerValue" id="${item.id}_labelHaloFillStart_opener" style="background-color: ${item.labelHaloFillStart}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="labelHaloFillEnd">
    <mjl:input type="hidden" param="labelHaloFillEnd" value="${item.labelHaloFillEnd}" id="${item.id}_labelHaloFillEnd" />
    <div class="colorPickerValue" id="${item.id}_labelHaloFillEnd_opener" style="background-color: ${item.labelHaloFillEnd}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="pointStrokeStart">
    <mjl:input type="hidden" param="pointStrokeStart" value="${item.pointStrokeStart}" id="${item.id}_pointStrokeStart" />
    <div class="colorPickerValue" id="${item.id}_pointStrokeStart_opener" style="background-color: ${item.pointStrokeStart}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="pointStrokeEnd">
    <mjl:input type="hidden" param="pointStrokeEnd" value="${item.pointStrokeEnd}" id="${item.id}_pointStrokeEnd" />
    <div class="colorPickerValue" id="${item.id}_pointStrokeEnd_opener" style="background-color: ${item.pointStrokeEnd}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonStrokeStart">
    <mjl:input type="hidden" param="polygonStrokeStart" value="${item.polygonStrokeStart}" id="${item.id}_polygonStrokeStart" />
    <div class="colorPickerValue" id="${item.id}_polygonStrokeStart_opener" style="background-color: ${item.polygonStrokeStart}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonStrokeEnd">
    <mjl:input type="hidden" param="polygonStrokeEnd" value="${item.polygonStrokeEnd}" id="${item.id}_polygonStrokeEnd" />
    <div class="colorPickerValue" id="${item.id}_polygonStrokeEnd_opener" style="background-color: ${item.polygonStrokeEnd}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonFillStart">
    <mjl:input type="hidden" param="polygonFillStart" value="${item.polygonFillStart}" id="${item.id}_polygonFillStart" />
    <div class="colorPickerValue" id="${item.id}_polygonFillStart_opener" style="background-color: ${item.polygonFillStart}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="polygonFillEnd">
    <mjl:input type="hidden" param="polygonFillEnd" value="${item.polygonFillEnd}" id="${item.id}_polygonFillEnd" />
    <div class="colorPickerValue" id="${item.id}_polygonFillEnd_opener" style="background-color: ${item.polygonFillEnd}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="pointSizeStart">
    <mjl:input param="pointSizeStart" type="text" />
  </mjl:dt>
  <mjl:dt attribute="pointSizeEnd">
    <mjl:input param="pointSizeEnd" type="text" />
  </mjl:dt>
<script type="text/javascript">
(function(){
  
  var picker = MDSS.ColorPicker.getInstance();

  picker.attach('${item.id}_labelHaloFillStart_opener', '${item.id}_labelHaloFillStart');
  picker.attach('${item.id}_labelHaloFillEnd_opener', '${item.id}_labelHaloFillEnd');

  picker.attach('${item.id}_fontFillStart_opener', '${item.id}_fontFillStart');
  picker.attach('${item.id}_fontFillEnd_opener', '${item.id}_fontFillEnd');

  picker.attach('${item.id}_pointStrokeStart_opener', '${item.id}_pointStrokeStart');
  picker.attach('${item.id}_pointStrokeEnd_opener', '${item.id}_pointStrokeEnd');
  
  picker.attach('${item.id}_polygonStrokeStart_opener', '${item.id}_polygonStrokeStart');
  picker.attach('${item.id}_polygonStrokeEnd_opener', '${item.id}_polygonStrokeEnd');

  picker.attach('${item.id}_polygonFillStart_opener', '${item.id}_polygonFillStart');
  picker.attach('${item.id}_polygonFillEnd_opener', '${item.id}_polygonFillEnd');
  

  
})();
</script>
</mjl:component>

<script type="text/javascript">
(function(){
	var required = ${required};
	var dts = YAHOO.util.Selector.query('dt.factoryAttribute');
	
	var toggle = function(type) {
	  var attributes = required[type];
	  for(var i=0; i<dts.length; i++)
	  {
	    var dt = dts[i];
	    var display = 'none';
	    for(var j=0; j<attributes.length; j++)
	    {
	      if(dt.id === attributes[j]+'DT')
	      {
	        display = 'block';
	        break;
	      }
	    }

	    dt.style.display = display;
	    dt.nextSibling.style.display = display;
	  }
  };	

  // initial config
  var select = document.getElementById('factories');
  var type = select.options[select.selectedIndex].value;
  toggle(type);

  YAHOO.util.Event.on(select, 'change', function(e){
    var select = e.target;
    var type = select.options[select.selectedIndex].value;
    toggle(type);
  });  
})();
</script>