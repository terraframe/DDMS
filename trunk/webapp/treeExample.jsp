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
	'Label 0',
	{type:'Text', label:'text label 1', title:'this is the tooltip for text label 1'},
	{type:'Text', label:'branch 1', title:'there should be children here', expanded:true, children:[
		'Label 1-0'
	]},
	{type:'Text',label:'YAHOO',title:'this should be an href', href:'http://www.yahoo.com', target:'somewhere new'},
	{type:'HTML',html:'<a href="developer.yahoo.com/yui">YUI</a>'},
	{type:'MenuNode',label:'branch 3',title:'this is a menu node', expanded:false, children:[
		'Label 3-0',
		'Label 3-1'
	]}
]);
tree.render();

function collect(node, nodes)
{
  if(node.hasChildren())
  {
    new YAHOO.util.DDTarget(node.getElId());
  
    var children = node.children;
    for(var i=0; i<children.length; i++)
    {
      var child = children[i];
      collect(child, nodes);
    }
  }
  else
  {
    new MDSS.TreeViewDD(node.getElId());
  }
}

var root = tree.getRoot();
var containerNodes = [];
collect(root, containerNodes);

}
  window.addEventListener('load', onloadHandler , false);

</script>

</head>
<body class="yui-skin-sam">

<div id='dragme'>testing</div>

  <div id="treeView"></div>
</body>
</html>