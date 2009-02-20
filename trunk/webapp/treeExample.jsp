<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/mojoLib.tld" prefix="mjl"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Ajax Example</title>

<jsp:include page="WEB-INF/templates/yuiIncludes.jsp" />

<script type="text/javascript" src="js/Terraframe_Mojo.js"></script>
<script type="text/javascript" src="js/GeoEntityTree.js"></script>
<script type="text/javascript">

  var data = [
    {type:'HTML',html:'Country 1', expanded:false, children:[
      {type:'HTML', html:'Province 1', expanded:false, children:[
        {type:'HTML', html:'District 1', expanded:false, children:[
          {type:'HTML', html:'Sub District 1'},
          {type:'HTML', html:'Sub District 2'}
        ]},
        {type:'HTML', html:'District 2', expanded:false, children:[
          {type:'HTML', html:'Sub District 3'},
          {type:'HTML', html:'Sub District 4'}
        ]}
      ]},
      {type:'HTML', html:'Province 2', expanded:false, children:[
        {type:'HTML', html:'District 3', expanded:false, children:[
          {type:'HTML', html:'Sub District 5'},
          {type:'HTML', html:'Sub District 6'}
        ]},
        {type:'HTML', html:'District 4', expanded:false, children:[
          {type:'HTML', html:'Sub District 7'},
          {type:'HTML', html:'Sub District 8'}
        ]}
      ]}
    ]},
    {type:'HTML',html:'Country 2'}
  ];
  

  YAHOO.util.Event.onDOMReady(function(){
    YAHOO.GeoEntityTree.initializeTree(data)},
  null, true);
</script>

</head>
<body class="yui-skin-sam">
  <div id="treeView"></div>
</body>
</html>