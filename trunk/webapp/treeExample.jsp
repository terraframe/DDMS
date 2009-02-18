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
<script type="text/javascript" src="js/TreeViewDnD.js"></script>
<script type="text/javascript">

  function onloadHandler(){
  
  
var tree = new YAHOO.widget.TreeView("treeView",[
	{type:'Text', label:'country 1', title:'country 1', expanded:true, children:[
	  {type:'Text', label:"province 1", title:"province 1"},
	  {type:'Text', label:"province 2", title:"province 2", expanded:true, children:[
        {type:'Text', label:"district 1", title:"district 1"},
        {type:'Text', label:"district 2", title:"district 2", expanded:true, children:[
          {type:'Text', label:"subdistrict 1", title:"subdistrict 1"}
        ]},  
	  ]}
	]},
	{type:'Text',label:'country 2',title:'country 2', expanded:true, children:[
	  {type:'Text', label:"province 3", title:"province 3"},
	  {type:'Text', label:"province 4", title:"province 4", expanded:true, children:[
        {type:'Text', label:"district 3", title:"district 3"},
        {type:'Text', label:"district 4", title:"district 4", expanded:true, children:[
          {type:'Text', label:"subdistrict 2", title:"subdistrict 2"}
        ]},  
      ]}
	]}
]);
tree.render();

MDSS.initTreeViewDnD(tree);

}
  window.addEventListener('load', onloadHandler , false);

</script>

</head>
<body class="yui-skin-sam">
  <div id="treeView"></div>
</body>
</html>