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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--TO USE ME: <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />--%>

<link rel="icon" type="image/png" href="./imgs/favicon.png" >

<!--CSS for Controls:-->
<link rel="stylesheet" type="text/css" href="js/yui/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/menu/assets/skins/sam/menu.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/autocomplete/assets/skins/sam/autocomplete.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/resize/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/colorpicker/assets/skins/sam/colorpicker.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/slider/assets/skins/sam/slider.css">
<!--
<link rel="stylesheet" type="text/css" href="js/yui/build/carousel/assets/skins/sam/carousel.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/imagecropper/assets/skins/sam/imagecropper.css">
-->
<link rel="stylesheet" type="text/css" href="js/yui/build/layout/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/tabview/assets/skins/sam/tabview.css">
<!--
<link rel="stylesheet" type="text/css" href="js/yui/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yui/build/editor/assets/skins/sam/editor.css">
-->
<!--JavaScript source files for the entire YUI Library:-->

<!--YUI Core (also aggregated in yahoo-dom-event.js; see readmes in the
YUI download for details on each of the aggregate files and their contents):-->
<script type="text/javascript" src="js/yui/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yui/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yui/build/event/event.js"></script>

<!--Utilities (also partialy aggregated utilities.js; see readmes in the
YUI download for details on each of the aggregate files and their contents):-->
<script type="text/javascript" src="js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="js/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="js/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="js/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="js/yui/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yui/build/imageloader/imageloader-min.js"></script>
<script type="text/javascript" src="js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="js/yui/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yui/build/selector/selector-min.js"></script>
<!--
<script type="text/javascript" src="js/yui/build/cookie/cookie-min.js"></script>
<script type="text/javascript" src="js/yui/build/get/get-min.js"></script>
<script type="text/javascript" src="js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="js/yui/build/yuiloader/yuiloader-min.js"></script>
-->

<!--YUI's UI Controls:-->
<script type="text/javascript" src="js/yui/build/container/container-min.js"></script>
<script type="text/javascript" src="js/yui/build/menu/menu.js"></script>
<script type="text/javascript" src="js/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="js/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="js/yui/build/datatable/datatable.js"></script>
<script type="text/javascript" src="js/yui/build/treeview/treeview-min.js"></script>
<script type="text/javascript" src="js/yui/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yui/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/yui/build/slider/slider-min.js"></script>
<script type="text/javascript" src="js/yui/build/colorpicker/colorpicker-min.js"></script>
<!--
<script type="text/javascript" src="js/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yui/build/uploader/uploader-min.js"></script>
<script type="text/javascript" src="js/yui/build/editor/editor-min.js"></script>
<script type="text/javascript" src="js/yui/build/imagecropper/imagecropper-min.js"></script>
<script type="text/javascript" src="js/yui/build/charts/charts-min.js"></script>
-->

<script type="text/javascript">
var locale = "<%=request.getLocale().toString()%>";
</script>

<script type="text/javascript" src="js/RunwaySDK.js"></script>
<script type="text/javascript" src="js/RunwaySDK_GIS.js"></script>
<script type="text/javascript" src="js/MDSS.js"></script>
<script type="text/javascript" src="js/Localized.js.jsp"></script>
<script type="text/javascript" src="js/navMenu.js"></script>
<script type="text/javascript" src="js/date.js"></script>
<script type="text/javascript" src="js/selectbox.js"></script>
<script type="text/javascript" src="js/calendarWidgets.js"></script>
<script type="text/javascript" src="js/dataTableWidget.js"></script>
<script type="text/javascript" src="js/AbstractTree.js"></script>
<script type="text/javascript" src="js/GeoEntityTree.js"></script>
<script type="text/javascript" src="js/GeoHierarchyTree.js"></script>
<script type="text/javascript" src="js/AbstractSelectSearch.js"></script>
<script type="text/javascript" src="js/SingleSelectSearch.js"></script>
<script type="text/javascript" src="js/MultipleSelectSearch.js"></script>
<script type="text/javascript" src="js/QueryPanel.js"></script>

<!--  Stuff from the web designer  -->
<link rel="stylesheet" type="text/css" href="js/yui/build/reset-fonts-grids/reset-fonts-grids.css">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/print_style.css" rel="stylesheet" type="text/css" media="print">

