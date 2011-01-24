<%@ taglib uri="/WEB-INF/tlds/runwayLib.tld" prefix="mjl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/mdssLib.tld" prefix="mdss"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="dss.vector.solutions.query.RangeCategoryDTO"%>

<%
  request.setAttribute("RangeClass", RangeCategoryDTO.CLASS);
%>

<%@page import="java.util.List"%>
<%@page import="com.runwaysdk.business.ValueObjectDTO"%>
<%@page import="dss.vector.solutions.query.QueryConstants"%>
<mjl:component item="${layer}" param="layer">
  <input type="hidden" id="layerId" value="${layer.id}" />
  <input type="hidden" id="hiddenStyleId" value="${styles.id}" />
<dl>
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
    <label><mdss:localize key="Calculate_Query_Info"></fmt:message></label>
  </dt>
  <dd>
    <mdss:localize key="Calculate" var="Localized_Calculate" />
    <mjl:command name="dss.vector.solutions.query.LayerController.calculateQueryInfoBtn"
      action="dss.vector.solutions.query.LayerController.calculateQueryInfo.mojo" value="${Localized_Calculate}" />
    <div id="queryInfo">
    </div>
  </dd>
  <mjl:dt attribute="showThematicValue">
    <mjl:boolean param="showThematicValue" />
  </mjl:dt>
  <mjl:dt attribute="renderAs">
    <mjl:group type="radio" var="current" valueAttribute="enumName" items="${renderAsOptions}" param="renderAs">
      <mjl:groupOption
        value="${currentEnumName}"
        checked="${mjl:contains(layer.renderAsEnumNames, current.enumName) ? 'checked' : 'false'}">
        ${current.displayLabel} 
      </mjl:groupOption>
    </mjl:group>
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
</dl>

<jsp:include page="../Styles/form.jsp"></jsp:include>
<hr />
<strong><mdss:localize key="Advanced_Legend_Options" /></strong>: <span id="${styles.id}_toggleLegend" class="clickable"><mdss:localize key="Toggle_Show" /></span>
<div id="${styles.id}_toggleLegendDiv"  style="display: block; margin-bottom: 10px;">
<dl>
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
</dl>
</div>

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

<div id="categories" style="margin-top: 10px;">
<strong><mdss:localize key="Add_Category" /></strong>: 
<c:choose>
<c:when test="${!isNewInstance}">

  <mdss:localize key="Exact_Category" var="Localized_Exact_Category" />

  <mjl:command id="exactCategory" value="${Localized_Exact_Category}" action="dss.vector.solutions.query.NonRangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mdss:localize key="Range_Category" var="Localized_Range_Category" />
  <mjl:command id="rangeCategory" value="${Localized_Range_Category}" action="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo" name="dss.vector.solutions.query.RangeCategoryController.newInstance.mojo.button" />
  <mdss:localize key="Generate" var="Localized_Generate" />
  <mjl:command value="${Localized_Generate}" id="${layer.id}_generate" action="dss.vector.solutions.query.LayerController.requestGenerate.mojo" name="dss.vector.solutions.query.LayerController.requestGenerate.mojo.button" />
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
 <i><mdss:localize key="Layer_Create_Required" /></i>
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
</script>