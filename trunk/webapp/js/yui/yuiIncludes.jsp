<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<link rel="stylesheet" type="text/css" href="/testproj/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="/testproj/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="/testproj/yui/build/yahoo/yahoo-min.js"></script> 
<script type="text/javascript" src="/testproj/yui/build/dom/dom-min.js"></script> 
<script type="text/javascript" src="/testproj/yui/build/event/event-min.js"></script> 

<script type="text/javascript" src="/testproj/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="/testproj/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="/testproj/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="/testproj/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" >
YAHOO.example.Data = {
    addresses: [
        {name:"John A. Smith", address:"1236 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan B. Jones", address:"3271 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob C. Uncle", address:"9996 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
        {name:"John D. Smith", address:"1623 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan E. Jones", address:"3217 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob F. Uncle", address:"9899 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
        {name:"John G. Smith", address:"1723 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan H. Jones", address:"3241 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob I. Uncle", address:"9909 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
        {name:"John J. Smith", address:"1623 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan K. Jones", address:"3721 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob L. Uncle", address:"9989 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
        {name:"John M. Smith", address:"1293 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan N. Jones", address:"3621 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob O. Uncle", address:"9959 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"},
        {name:"John P. Smith", address:"6123 Some Street", city:"San Francisco", state:"CA", amount:5, active:"yes", colors:["red"], last_login:"4/19/2007"},
        {name:"Joan Q. Jones", address:"3281 Another Ave", city:"New York", state:"NY", amount:3, active:"no", colors:["red","blue"], last_login:"2/15/2006"},
        {name:"Bob R. Uncle", address:"9989 Random Road", city:"Los Angeles", state:"CA", amount:0, active:"maybe", colors:["green"], last_login:"1/23/2004"}
    ]
}

</script>