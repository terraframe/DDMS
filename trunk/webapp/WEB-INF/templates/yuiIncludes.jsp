<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%--TO USE ME: <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />--%>
<link rel="icon" type="image/png" href="/favicon.png" >

<!--CSS for Controls:--> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/menu/assets/skins/sam/menu.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/autocomplete/assets/skins/sam/autocomplete.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/button/assets/skins/sam/button.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/calendar/assets/skins/sam/calendar.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/carousel/assets/skins/sam/carousel.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/colorpicker/assets/skins/sam/colorpicker.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/datatable/assets/skins/sam/datatable.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/editor/assets/skins/sam/editor.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/imagecropper/assets/skins/sam/imagecropper.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/layout/assets/skins/sam/layout.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/paginator/assets/skins/sam/paginator.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/resize/assets/skins/sam/resize.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/tabview/assets/skins/sam/tabview.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/treeview/assets/skins/sam/treeview.css"> 
 
<!--JavaScript source files for the entire YUI Library:--> 
 
<!--YUI Core (also aggregated in yahoo-dom-event.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="/MDSS/js/yui/build/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/dom/dom-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/event/event-min.js"></script> 
 
<!--Utilities (also partialy aggregated utilities.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="/MDSS/js/yui/build/element/element-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/connection/connection-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/cookie/cookie-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/datasource/datasource-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/get/get-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/history/history-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/imageloader/imageloader-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/json/json-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/resize/resize-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/selector/selector-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/yuiloader/yuiloader-min.js"></script> 
 
<!--YUI's UI Controls:--> 
<script type="text/javascript" src="/MDSS/js/yui/build/container/container-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/menu/menu-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/autocomplete/autocomplete-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/button/button-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/charts/charts-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/colorpicker/colorpicker-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/datatable/datatable-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/editor/editor-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/imagecropper/imagecropper-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/layout/layout-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/paginator/paginator-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/slider/slider-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/treeview/treeview-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/uploader/uploader-min.js"></script> 


<script type="text/javascript" src="js/Terraframe_Mojo.js"></script>
<script type="text/javascript" src="js/MDSS.js"></script>
<script type="text/javascript">var locale = "<%=request.getLocale().toString()%>";</script>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="/MDSS/js/addCalendarWidgets.js"></script>
<script type="text/javascript" src="js/dataTableWidget.js"></script>


<!--  Stuff from the web designer  -->
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/reset-fonts-grids/reset-fonts-grids.css">
<link href="/MDSS/css/style.css" rel="stylesheet" type="text/css">

