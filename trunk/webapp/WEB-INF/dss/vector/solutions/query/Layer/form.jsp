<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>

<%
  request.setAttribute("RangeClass", RangeCategoryDTO.CLASS);
%>

<%@page import="java.util.List"%>
<%@page import="com.terraframe.mojo.business.ValueObjectDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<mjl:component item="${layer}" param="layer">
  <input type="hidden" id="layerId" value="${layer.id}" />
  <mjl:dt attribute="layerName">
    <mjl:input type="text" param="layerName" />
  </mjl:dt>
  <mjl:dt attribute="savedSearch">
    <mjl:select id="savedSearchList" var="current" valueAttribute="savedQueryId" items="${queryList}" param="savedSearch">
      <mjl:option>
        ${current.queryName}
      </mjl:option>
    </mjl:select>
  </mjl:dt>
  <mjl:dt attribute="geoHierarchy">
    <mjl:input type="hidden" param="mdAttribute" id="mdAttributeId" value="${mdAttributeId}" />
    <mjl:input type="hidden" param="geoHierarchy" id="geoHierarchyId" value="${geoHierarchyId}" />
    <select id="attrGeoSelect">
      <c:forEach items="${attrGeos}" var="attrGeo">
        <c:choose>
          <c:when test="${currentAttributeGeoHierarchy == attrGeo.concatId}">
            <option selected="selected" value="${attrGeo.mdAttributeId}:${attrGeo.geoHierarchyId}">
              ${attrGeo.geoHierarchyDisplayLabel} (${attrGeo.attributeDisplayLabel})
            </option>
          </c:when>
          <c:otherwise>
            <option value="${attrGeo.mdAttributeId}:${attrGeo.geoHierarchyId}">
              ${attrGeo.geoHierarchyDisplayLabel} (${attrGeo.attributeDisplayLabel})
            </option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt attribute="thematicUserAlias">
    <select name="layer.thematicUserAlias" id="thematicVariables">
      <option value=""></option>
      <c:forEach items="${thematicVars}" var="thematicVar">
        <c:choose>
          <c:when test="${thematicVar.userAlias == layer.thematicUserAlias}">
            <option value="${thematicVar.userAlias}" selected="selected">${thematicVar.displayLabel}</option>
          </c:when>
          <c:otherwise>
            <option value="${thematicVar.userAlias}">${thematicVar.displayLabel}</option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
  </mjl:dt>
  <dt>
    <label><fmt:message key="Calculate_Query_Info"></fmt:message></label>
  </dt>
  <dd>
    <mjl:command name="dss.vector.solutions.query.LayerController.calculateQueryInfoBtn"
      action="dss.vector.solutions.query.LayerController.calculateQueryInfo.mojo" value="Calculate"/>
    <div id="queryInfo">
    </div>
  </dd>
  <mjl:dt attribute="showThematicValue">
    <mjl:boolean param="showThematicValue" />
  </mjl:dt>
  <mjl:dt attribute="renderAs">
    <mjl:radioGroup var="current" valueAttribute="enumName" items="${renderAsOptions}" param="renderAs">
      <mjl:radioOption
        value="${currentEnumName}"
        checked="${mjl:contains(layer.renderAsEnumNames, current.enumName) ? 'checked' : 'false'}">
        ${current.displayLabel} 
      </mjl:radioOption>
    </mjl:radioGroup>
  </mjl:dt>  
  <mjl:dt attribute="clipToBaseLayer">
    <mjl:boolean param="clipToBaseLayer" />
  </mjl:dt>
  <mjl:dt attribute="addToBBox">
    <mjl:boolean param="addToBBox" />
  </mjl:dt>
  <mjl:dt attribute="opacity">
    <div id="${layer.id}_opacitySliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${layer.id}_opacityThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${layer.id}_opacityDisplay" class="opacitySliderDisplay">&nbsp;</span>
      <mjl:input type="hidden" param="opacity" id="${layer.id}_opacity" />
    </div>
  </mjl:dt>
  <mjl:dt attribute="enableLegend">
    <mjl:boolean param="enableLegend"/>
  </mjl:dt>
  <mjl:dt attribute="legendTitle">
    <mjl:input type="text" param="legendTitle"/>
  </mjl:dt>
  
  <mjl:dt attribute="legendTitleFontFill">
    <mjl:input type="hidden" param="legendTitleFontFill" value="${layer.legendTitleFontFill}" id="${layer.id}_legendTitleFontFill" />
    <div class="colorPickerValue" id="${layer.id}_legendTitleFontFill_opener" style="background-color: ${layer.legendTitleFontFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="legendTitleFontFamily">
    <select name="layer.legendTitleFontFamily">
      <c:forEach items="${fontFamilies}" var="font">
        <option value="${font}" <c:if test="${layer.legendTitleFontFamily == font}">selected="selected"</c:if>>${font}</option>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt attribute="legendTitleFontSize">

    <div id="${layer.id}_legendTitleFontSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${layer.id}_legendTitleFontSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${layer.id}_legendTitleFontSizeDisplay"></span>
      <mjl:input type="hidden" param="legendTitleFontSize" id="${layer.id}_legendTitleFontSize" />
    </div>
  
  </mjl:dt>
  <mjl:dt attribute="legendTitleFontStyles">
  
    <mjl:select var="current" valueAttribute="enumName" items="${allFontStyles}" param="legendTitleFontStyles">
      <mjl:option selected="${mjl:contains(layer.legendTitleFontStylesEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>  
  
  </mjl:dt>  
  
  <mjl:dt attribute="legendFontFill">
    <mjl:input type="hidden" param="legendFontFill" value="${layer.legendFontFill}" id="${layer.id}_legendFontFill" />
    <div class="colorPickerValue" id="${layer.id}_legendFontFill_opener" style="background-color: ${layer.legendFontFill}">&nbsp;</div>
  </mjl:dt>
  <mjl:dt attribute="legendFontFamily">
    <select name="layer.legendFontFamily">
      <c:forEach items="${fontFamilies}" var="font">
        <option value="${font}" <c:if test="${layer.legendFontFamily == font}">selected="selected"</c:if>>${font}</option>
      </c:forEach>
    </select>
  </mjl:dt>
  <mjl:dt attribute="legendFontSize">

    <div id="${layer.id}_legendFontSizeSliderBG" class="yui-h-slider" style="width: 100px">
      <div id="${layer.id}_legendFontSizeThumb" class="yui-slider-thumb"><img src="imgs/thumb-n.gif"></div>
    </div>
    <div>
      <span id="${layer.id}_legendFontSizeDisplay"></span>
      <mjl:input type="hidden" param="legendFontSize" id="${layer.id}_legendFontSize" />
    </div>
  
  </mjl:dt>
  <mjl:dt attribute="legendFontStyles">
  
    <mjl:select var="current" valueAttribute="enumName" items="${allFontStyles}" param="legendFontStyles">
      <mjl:option selected="${mjl:contains(layer.legendFontStylesEnumNames, current.enumName) ? 'selected' : 'false'}">
        ${current.displayLabel}
      </mjl:option>
    </mjl:select>  
  
  </mjl:dt>
  <mjl:dt attribute="showLegendBorder">
    <mjl:boolean param="showLegendBorder"/>
  </mjl:dt>
  <mjl:dt attribute="createRawLegend">
    <mjl:boolean param="createRawLegend"/>
  </mjl:dt>
  <mjl:dt attribute="legendColor">
    <select name="layer.legendColor">
      <c:forEach items="${legendColors}" var="color">
        <option <c:if test="${currentLegendColor == color.mdAttributeId}">selected="selected"</c:if> value="${color.mdAttributeId}">${color.display}</option>
      </c:forEach>
    </select>
  </mjl:dt>


<script type="text/javascript">
(function(){
  MDSS.MapPanel.attachOpacitySlider('${layer.id}_opacity');

  var picker = MDSS.ColorPicker.getInstance();
  picker.attach('${layer.id}_legendFontFill_opener', '${layer.id}_legendFontFill');
  picker.attach('${layer.id}_legendTitleFontFill_opener', '${layer.id}_legendTitleFontFill');

  MDSS.MapPanel.attach50Slider('${layer.id}_legendFontSize');
  MDSS.MapPanel.attach50Slider('${layer.id}_legendTitleFontSize');
  
})();
</script>
</mjl:component>

<strong><fmt:message key="Advanced" /></strong>: <span id="${styles.id}_toggle" class="clickable"><fmt:message key="Toggle_Show" /></span>
<div id="${styles.id}_toggleDiv" style="display: block; margin-bottom: 10px;">
  <jsp:include page="../Styles/form.jsp"></jsp:include>
</div>

<div id="categories" style="margin-top: 10px;">
<strong><fmt:message key="Add_Category" /></strong>: 
<c:choose>
<c:when test="${!isNewInstance}">

  <mjl:command id="exactCategory" value="Exact_Category" action="dss.vector.solutions.query.NonRangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mjl:command id="rangeCategory" value="Range_Category" action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mjl:command value="Generate" id="${layer.id}_generate" action="dss.vector.solutions.query.LayerController.requestGenerate.mojo" name="dss.vector.solutions.query.LayerController.requestGenerate.mojo.button" />
  <ul id="categoryList">
    <c:forEach items="${categories}" var="category">
      <li>
      <c:set var="category" value="${category}" scope="request"></c:set>
      <c:choose>
        <c:when test="${category.type == RangeClass}">
          <jsp:include page="../RangeCategory/summaryView.jsp"></jsp:include>
        </c:when>
        <c:otherwise>
          <jsp:include page="../NonRangeCategory/summaryView.jsp"></jsp:include>
        </c:otherwise>
      </c:choose>
    </li>
    </c:forEach>
  </ul>
</c:when>
<c:otherwise>
 <i><fmt:message key="Layer_Create_Required" /></i>
 <ul id="categoryList"></ul>
</c:otherwise>
</c:choose>
</div>

<script type="text/javascript">

if(${!isNewInstance})
{
  YAHOO.util.Event.on('thematicVariables', 'change', function(e){
    var el = e.target;
    var disabled = el.selectedIndex === 0;
    document.getElementById('${layer.id}_generate').disabled = disabled;
    document.getElementById('exactCategory').disabled = disabled;
    document.getElementById('rangeCategory').disabled = disabled;
  });
	
	if(${!hasThematic})
	{
    document.getElementById('${layer.id}_generate').disabled = true;
    document.getElementById('exactCategory').disabled = true;
    document.getElementById('rangeCategory').disabled = true;
	}
}
	
// This timeout is used to ensure that all other DOM manipulating JS calls
// can be completed before executing the logic below. Otherwise, the sliders
// and color pickers will not work properly.
setTimeout(function(){

  document.getElementById('${styles.id}_toggleDiv').style.display = 'none';

  YAHOO.util.Event.on('${styles.id}_toggle', 'click', function(e){
    var el = e.target;
    var div = document.getElementById('${styles.id}_toggleDiv');
    if(div.style.display == 'none')
    {
      el.innerHTML = MDSS.Localized.Toggle_Hide;
      div.style.display = 'block';
    }
    else
    {
      el.innerHTML = MDSS.Localized.Toggle_Show;
      div.style.display = 'none';
    }
  });

}, 150);
</script>