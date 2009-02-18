<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--TO USE ME: <jsp:include page="/WEB-INF/templates/yuiIncludes.jsp" />--%>

<link rel="icon" type="image/png" href="/MDSS/imgs/favicon.png"/>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

 <script type="text/javascript" src="js/Terraframe_Mojo.js"></script>


<!--CSS source files for the entire YUI Library--> 
<!--CSS Foundation: (also partially aggegrated in reset-fonts-grids.css; does not include base.css)--> 

<!--<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/reset/reset-min.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/base/base-min.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/fonts/fonts-min.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/grids/grids-min.css"> -->
<link rel="stylesheet" type="text/css" href="/MDSS/yui/build/assets/skins/sam/skin.css">     

 
<!--CSS for Controls:--> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/container/assets/skins/sam/container.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/menu/assets/skins/sam/menu.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/autocomplete/assets/skins/sam/autocomplete.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/button/assets/skins/sam/button.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/calendar/assets/skins/sam/calendar.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/carousel/assets/skins/sam/carousel.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/colorpicker/assets/skins/sam/colorpicker.css"> 
 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/editor/assets/skins/sam/editor.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/imagecropper/assets/skins/sam/imagecropper.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/layout/assets/skins/sam/layout.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/paginator/assets/skins/sam/paginator.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/resize/assets/skins/sam/resize.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/tabview/assets/skins/sam/tabview.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/treeview/assets/skins/sam/treeview.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/datatable/assets/skins/sam/datatable.css">
 
<!--JavaScript source files for the entire YUI Library:--> 
 
<!--YUI Core (also aggregated in yahoo-dom-event.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="/MDSS/js/yui/build/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/dom/dom-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/event/event-min.js"></script> 
 
<!--Utilities (also partialy aggregated utilities.js; see readmes in the 
YUI download for details on each of the aggregate files and their contents):--> 
<script type="text/javascript" src="/MDSS/js/yui/build/element/element-beta-min.js"></script> 
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
<script type="text/javascript" src="/MDSS/js/yui/build/selector/selector-beta-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/yuiloader/yuiloader-min.js"></script> 
 
<!--YUI's UI Controls:--> 
<script type="text/javascript" src="/MDSS/js/yui/build/container/container-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/menu/menu-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/autocomplete/autocomplete-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/button/button-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/charts/charts-experimental-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/colorpicker/colorpicker-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/datatable/datatable-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/editor/editor-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/imagecropper/imagecropper-beta-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/layout/layout-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/paginator/paginator-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/slider/slider-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/treeview/treeview-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/uploader/uploader-experimental-min.js"></script> 
 
<!--YUI Developer Tools: Logging, Testing and Profiling --> 
<!--These are all components that are useful in developing and debugging 
    your work; however, none of these is designed to be a user-facing 
    component.  They are specifically targeted toward easing your work 
    as a developer.--> 
<!--css--> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/logger/assets/skins/sam/logger.css"> 
<link rel="stylesheet" type="text/css" href="/MDSS/js/yui/build/profilerviewer/assets/skins/sam/profilerviewer.css"> 
<!--js--> 
<script type="text/javascript" src="/MDSS/js/yui/build/logger/logger-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/profiler/profiler-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/profilerviewer/profilerviewer-beta-min.js"></script> 
<script type="text/javascript" src="/MDSS/js/yui/build/yuitest/yuitest-min.js"></script> 
 
<!--.swf file for the YUI Charts Control--> 
<script type="text/javascript"> 
YAHOO.widget.Chart.SWFURL = "/MDSS/js/yui/build/charts/assets/charts.swf"; 
</script> 

<style type="text/css" media="screen">
    #cal1Container {
        position: absolute;
        display: none;
        background-color:WHITE;
    }
     #cal1Container {
        margin: 1em;
    }
    textarea {
        width: 100%;
    }
    #cal1Container {
        z-index: 500;
    }
</style>
<script type="text/javascript" src="js/Menu.js"></script>
<script type="text/javascript" src="/MDSS/js/addCalendarWidgets.js"></script> 