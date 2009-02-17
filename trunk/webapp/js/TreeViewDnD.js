// initialize a namespace (TODO push this in a root js file)
var MDSS = MDSS || {};

/**
 * Class for a draggable TreeView node.
 */
MDSS.TreeViewDD = function(){
  MDSS.TreeViewDD.superclass.constructor.apply(this, arguments);
};

YAHOO.extend(MDSS.TreeViewDD, YAHOO.util.DD, {
   
   /**
    * Mark the target as droppable.
    */
   onDragEnter: function(e, id) {
     alert('entered');
   },
   
   /**
    * Adds the node to the parent list, and removes
    * the old entry.
    */
   onDragDrop: function(e, id) {
     Dom.setStyle(id, 'borderColor', 'black');
   }
});