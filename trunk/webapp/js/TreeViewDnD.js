// initialize a namespace (TODO push this in a root js file)
var MDSS = MDSS || {};

/**
 * Map of TreeView targets for draggable nodes.
 */
MDSS.TreeViewTargets = {};

/**
 * Initializes the Drag and Drop functionality
 * for the given tree.
 */
MDSS.initTreeViewDnD = function(tree)
{
  function collect(node){
    if(node.hasChildren())
    {
      if(!(node instanceof YAHOO.widget.RootNode))
      {
        var target = new MDSS.TreeViewDDT(node, tree);
        MDSS.TreeViewTargets[target.id] = target;
      }
    
      var children = node.children;
      for(var i=0; i<children.length; i++)
      {
        var child = children[i];
        collect(child);
      }
    }
    else
    {
      new MDSS.TreeViewDD(node, tree);
    }
  }
  
  collect(tree.getRoot());
}

MDSS.TreeViewDDT = function(treeNode, tree){
  this.treeNode = treeNode;
  this.tree = tree;
  MDSS.TreeViewDDT.superclass.constructor.call(this, treeNode.getElId());
}
YAHOO.extend(MDSS.TreeViewDDT, YAHOO.util.DDTarget, {
  markAsTarget : function()
  {
    //var el = new YAHOO.util.Element(this.getDragEl());
    //el.setStyle('border', '1px solid red');
  },
  
  unmarkAsTarget : function()
  {
    //var el = new YAHOO.util.Element(this.getDragEl());
    //el.setStyle('border', 'none');  
  }
});

/**
 * Class for a draggable TreeView node.
 * 
 * @node The TreeView node.
 */
MDSS.TreeViewDD = function(treeNode, tree){
  this.treeNode = treeNode;
  this.tree = tree;
  MDSS.TreeViewDD.superclass.constructor.call(this, treeNode.getElId());
};
YAHOO.extend(MDSS.TreeViewDD, YAHOO.util.DDProxy, {
  
  /**
   * Marks the target as droppable.
   */
  onDragEnter : function(e, id) {
    if(id in MDSS.TreeViewTargets)
    {
      var target = MDSS.TreeViewTargets[id];
      target.markAsTarget();
    }
  },
  
  /**
   * Adds the node to the parent list, and removes
   * the old entry.
   */
  onDragDrop : function(e, id) {
    if(id in MDSS.TreeViewTargets)
    {
      var target = MDSS.TreeViewTargets[id];
      var parent = this.treeNode.parent;
      
      this.tree.popNode(this.treeNode);
      
      // append the cloned node
      var targetNode = target.treeNode;
      targetNode.appendChild(this.treeNode);

      parent.refresh();
      targetNode.refresh();
      
      target.unmarkAsTarget();
    }
  },
  
  /**
   * Unmarks the target as droppable.
   */
  onDragOut:function(e, id)
  {
    if(id in MDSS.TreeViewTargets)
    {
      var target = MDSS.TreeViewTargets[id];
      target.unmarkAsTarget();
    }
  }
});