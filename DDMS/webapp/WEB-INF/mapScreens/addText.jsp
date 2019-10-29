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
<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<mjl:form name="${freeText.id}_form" id="${freeText.id}_form" method="POST">
<dl>
<mjl:component item="${freeText}" param="freeText">

  <mjl:dt attribute="customText">
    <mjl:input type="type" param="customText"/>
  </mjl:dt>

  <mjl:dt attribute="textFontFill">
    <mjl:input type="hidden" param="textFontFill" value="${freeText.textFontFill}" id="${freeText.id}_textFontFill" />
    <div class="colorPickerValue" id="${freeText.id}_textFontFill_opener" style="background-color: ${freeText.textFontFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="textFontFamily">
    <select name="freeText.textFontFamily">
      <c:forEach items="${fontFamilies}" var="font">
        <option value="${font}" <c:if test="${freeText.textFontFamily == font}">selected="selected"</c:if>>${font}</option>
      </c:forEach>
    </select>
     
  </mjl:dt>
  <mjl:dt attribute="textFontSize">

    <div id="${freeText.id}_textFontSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${freeText.id}_textFontSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${freeText.id}_textFontSizeDisplay"></span>
      <mjl:input type="hidden" param="textFontSize" id="${freeText.id}_textFontSize" />
    </div>

  </mjl:dt>
  <mjl:dt attribute="textFontStyles">
    <mjl:select var="current" valueAttribute="enumName" items="${allFontStyles}" param="textFontStyles">
      <mjl:option selected="${mjl:contains(freeText.textFontStylesEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>
  </mjl:dt>


<script type="text/javascript">
(function(){
  
  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${freeText.id}_textFontFill_opener', '${freeText.id}_textFontFill');

  MDSS.MapPanel.attach50Slider('${freeText.id}_textFontSize');

})();
</script>

</mjl:component>
</dl>
<mdss:localize key="Create" var="Localized_Create" />
<mjl:command value="${Localized_Create}" name="dss.vector.solutions.query.MappingController.form.setText.btn.mojo" action="dss.vector.solutions.query.MappingController.setText.mojo" />
</mjl:form>